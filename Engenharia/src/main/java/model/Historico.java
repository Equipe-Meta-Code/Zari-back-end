package model;

public class Historico {
    private String emailHistorico;
    private String documento;
    private String dataEHora;


    public Historico(String emailHistorico, String documento, String dataEHora) {
        this.emailHistorico = emailHistorico;
        this.documento = documento;
        this.dataEHora = dataEHora;
    }

    public String getDataEHora() {
		return dataEHora;
	}

	public void setDataEHora(String dataEHora) {
		this.dataEHora = dataEHora;
	}

    public String getEmailHistorico() {
        return emailHistorico;
    }

    public void setEmailHistorico(String emailHistorico) {
        this.emailHistorico = emailHistorico;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


}
