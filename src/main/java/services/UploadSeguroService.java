package services;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadSeguroService {

	private static int KB = 1024; // representa o tamanho em KB
	private static int MB = 1024 * 1024; // representa o tamanho em MB

	private String diretorio; // Nome do diretorio onde os arquivos vão ser salvos no servidor
	private String nomeArquivo;

	private int tamanhoMaximo; // Tamanho máximo permitido para o upload de arquivos em MB
	private String extensoesPermitidas; // Para receber as extensões de arquivo que serão aceitas

	// Para guardar o arquivo original quando for feito o upload de um arquivo com o
	// mesmo nome (para ser usado para recuperar caso ocorra algum erro com o
	// arquivi novo)
	private String nomeOriginal;
	private File arquivoTemp = null;

	// Variável que vai ser usada para repassar mensagens de erro
	private String erro = null;

	private List<FileItem> formItems = null; // O request só pode passar uma vez pelo método parseRequest, então estou
												// guardando essa lista para poder utilizar depois dela ser criada uma
												// vez
	
	private String encoding = null;

	public UploadSeguroService(String diretorio, int tamanhoMaximo, String extensoesPermitidas) {
		this.diretorio = diretorio;
		this.tamanhoMaximo = tamanhoMaximo;
		this.extensoesPermitidas = extensoesPermitidas;
	}

	public boolean doUpload(HttpServletRequest request, ServletContext context) {

		if (request.getContentType() == null)
			return false;

		// Verifica se foi enviada alguma informação e se no formulário foi enviado o
		// atributo enctype="multipart/form-data" (necessário para upload de arquivos)
		if (ServletFileUpload.isMultipartContent(request)) {

			// Objeto responsável por gerenciar os arquivos em disco
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4 * KB); // Define o tamanho limite para que os arquivos fiquem armazenados na
												// memória ou em disco (temporariamente enquanto ele não foi salvo no
												// diretório ainda)
			factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); // Define uma pasta temporária para
																					// guardar os arquivos enquanto
																					// ainda não forem salvos

			// Objeto que gerencia o upload de arquivos vindo de uma requisição
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(tamanhoMaximo * MB); // Define o tamanho máximo permitido para arquivos

			String caminhoDiretorio = context.getRealPath("") + File.separator + diretorio;

			File diretorioUpload = new File(caminhoDiretorio);
			// Verifica se o diretório existe, se não existe ele é criado
			if (!diretorioUpload.exists()) {
				diretorioUpload.mkdir();
			}

			try {
				// Verifico se essa lista não foi criada antes, para assim pegar o valor dela
				// pela primeira vez
				if (formItems == null) {
					// Obtem a lista de items que foram enviados na requisição
					formItems = upload.parseRequest(request);
				}

				// Verifica se item foram enviados na req
				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {

						// Verifica se o item NÃO representa um campo simples do formulário (ou seja, é
						// um campo do tipo file)
						if (!item.isFormField()) {
							// Obtem o nome do arquivo enviado (isso serve para pegar apenas o nome do
							// arquivo e não o endereço na máquina de quem enviou)
							String nomeArquivo = new File(item.getName()).getName();

							if (!isArquivoPermitido(nomeArquivo)) { // Verifica se o arquivo NÃO possui o formato
																	// permitido
								setErro("Arquivo com formato n�o permitido.");
								return false;
							}

							// Caminho onde o arquivo vai ser salvo, o que inclui o diretório passado
							String caminhoArquivo = caminhoDiretorio + File.separator + new Date().getTime() + nomeArquivo;

							File arquivo = new File(caminhoArquivo);

							// Verifica se já existia um arquivo com o mesmo nome.
							// Se for o caso, crio um arquivo temporário para que o arquivo possa ser
							// reescrito com o mesmo nome.
							if (arquivo.exists()) {
								// Para que possamos sobrescrever o arquivo, caso ele possua um mesmo nome de
								// outro arquivo
								// Salvando o arquivo original para caso ocorra algum problema ao salvar o
								// arquivo
								nomeOriginal = arquivo.getName();
								arquivoTemp = new File("temp" + nomeOriginal);
								arquivo.renameTo(arquivoTemp);
								arquivo = new File(caminhoArquivo);
							}

							item.write(arquivo);
							this.nomeArquivo = diretorio + File.separator + arquivo.getName();
							if (arquivoTemp != null) {
								arquivoTemp.deleteOnExit();
							}
						}
					}
				}

			} catch (FileUploadBase.SizeLimitExceededException e) {
				setErro("Tamanho de arquivo excedido");
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				if (arquivoTemp != null) {
					// Voltando o arquivo para o nome original, caso tenha tido alguma falha na hora
					// de salvar o novo
					File arquivoOriginal = new File(nomeOriginal);
					arquivoTemp.renameTo(arquivoOriginal);
					arquivoTemp = null;
				}
				setErro("Ocorreu um erro no upload do arquivo: " + e.getMessage());
				e.printStackTrace();
				return false;
			}

		} else {
			setErro("Seu formul�rio n�o envia arquivos.");
			return false;
		}

		return true;
	}

	public boolean isArquivoPermitido(String nomeArquivo) {
		String nome = nomeArquivo.toLowerCase(); // Coloca o nome do arquivo em minúsculo

		String[] extensoes = extensoesPermitidas.split(","); // Separa as extensões, que devem ser separadas por vírgula

		for (int i = 0; i < extensoes.length; i++) {
			if (nome.endsWith("." + extensoes[i].trim())) { // Verifica se o arquivo termina com alguma das extensões
															// passadas
				return true;
			}
		}
		// Se chegou aqui, significa que o arquivo não possui uma extensão permitida
		return false;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getParameter(HttpServletRequest request, String parameterName) {
		try {
			// Verifico se essa lista não foi criada antes, para assim pegar o valor dela
			// pela primeira vez
			if (formItems == null) {
				// Objeto responsável por gerenciar os arquivos em disco
				// Mesmo que não vamos trabalhar com arquivos nesse método, é necessário para o
				// ServletFileUpload
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// Objeto que gerencia o upload de arquivos vindo de uma requisição
				ServletFileUpload upload = new ServletFileUpload(factory);

				// Obtem a lista de items que foram enviados na requisição
				formItems = upload.parseRequest(request);
			}

			// Verifica se item foram enviados na req
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {

					// Verifica se o item representa um campo simples do formulário (ou seja, é um
					// campo que NÃO é do tipo file)
					// Além disso, verifica se o campo possui o nome passado no campo
					if (item.isFormField() && item.getFieldName().equals(parameterName)) {
						// Retorna o valor do parâmetro do formulário que possui o nome passado
						// Se o atributo encoding tiver sido atribuido, então ele é passado. Isso serve para evitar problemas com acentos.
						if(this.encoding != null)
							return item.getString(this.encoding);
						else
							return item.getString();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// Caso não exista um campo normal com o nome passado, retorna null assim como
		// ocorre com o request.getParameter normal
		return null;
	}

	// Semelhante ao método doUpload original, mas esse permite fazer mais do que um
	// upload caso o formulário tenha um campo do tipo file a mais
	// É necessário passar o nome do campo (parameterName). O nome do arquivo pode
	// ser obtido logo após chamar esse método com o getNomeArquivo(), mas esse
	// método deve ser chamado antes de chamar esse método novamente porque ao
	// chamar novamente o nome muda
	public boolean doUploadParameter(HttpServletRequest request, ServletContext context, String parameterName) {

		if (request.getContentType() == null)
			return false;

		// Objeto responsável por gerenciar os arquivos em disco
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Define o tamanho limite para que os arquivos fiquem armazenados na memória ou
		// em disco (temporariamente enquanto ele não foi salvo no diretório ainda)
		factory.setSizeThreshold(4 * KB);

		// Define uma pasta temporária para guardar os arquivos enquanto ainda não forem
		// salvos
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// Objeto que gerencia o upload de arquivos vindo de uma requisição
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(tamanhoMaximo * MB); // Define o tamanho máximo permitido para arquivos
		
		// Para inserir o encoding para corrigir ecento. Para isso, deve-se chamar o setEncoding antes.
		if(this.encoding != null)
			upload.setHeaderEncoding(encoding);

		String caminhoDiretorio = context.getRealPath("") + File.separator + diretorio;

		File diretorioUpload = new File(caminhoDiretorio);
		// Verifica se o diretório existe, se não existe ele é criado
		if (!diretorioUpload.exists()) {
			diretorioUpload.mkdir();
		}

		// Verifica se foi enviada alguma informação e se no formulário foi enviado o
		// atributo enctype="multipart/form-data" (necessário para upload de arquivos)
		if (ServletFileUpload.isMultipartContent(request)) {

			try {
				// Verifico se essa lista não foi criada antes, para assim pegar o valor dela
				// pela primeira vez
				if (formItems == null) {
					// Obtem a lista de items que foram enviados na requisição
					formItems = upload.parseRequest(request);
				}

				// Verifica se item foram enviados na req
				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {

						// Verifica se o item NÃO representa um campo simples do formulário (ou seja, é
						// um campo do tipo file)
						if (!item.isFormField() && item.getFieldName().equals(parameterName)) {
							// Obtem o nome do arquivo enviado (isso serve para pegar apenas o nome do
							// arquivo e não o endereço na máquina de quem enviou)
							String nomeArquivo = new File(item.getName()).getName();

							if (!isArquivoPermitido(nomeArquivo)) { // Verifica se o arquivo NÃO possui o formato
																	// permitido
								setErro("Arquivo com formato não permitido");
								return false;
							}

							// Caminho onde o arquivo vai ser salvo, o que inclui o diretório passado
							String caminhoArquivo = caminhoDiretorio + File.separator + nomeArquivo;

							File arquivo = new File(caminhoArquivo);

							// Verifica se já existia um arquivo com o mesmo nome.
							// Se for o caso, crio um arquivo temporário para que o arquivo possa ser
							// reescrito com o mesmo nome.
							if (arquivo.exists()) {
								// Para que possamos sobrescrever o arquivo, caso ele possua um mesmo nome de
								// outro arquivo
								// Salvando o arquivo original para caso ocorra algum problema ao salvar o
								// arquivo
								nomeOriginal = arquivo.getName();
								arquivoTemp = new File("temp" + nomeOriginal);
								arquivo.renameTo(arquivoTemp);
								arquivo = new File(caminhoArquivo);
							}

							item.write(arquivo);
							this.nomeArquivo = diretorio + File.separator + arquivo.getName();
							if (arquivoTemp != null) {
								arquivoTemp.deleteOnExit();
							}
						}
					}
				}

			} catch (FileUploadBase.SizeLimitExceededException e) {
				setErro("Tamanho de arquivo excedido");
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				if (arquivoTemp != null) {
					// Voltando o arquivo para o nome original, caso tenha tido alguma falha na hora
					// de salvar o novo
					File arquivoOriginal = new File(nomeOriginal);
					arquivoTemp.renameTo(arquivoOriginal);
					arquivoTemp = null;
				}
				setErro("Ocorreu um erro no upload do arquivo: " + e.getMessage());
				e.printStackTrace();
				return false;
			}

		} else {
			setErro("Seu formulário não envia arquivos");
			return false;
		}

		return true;
	}

}