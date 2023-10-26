package model;

public class Historico {
    private String emailHistorico;
    private String documento;


    public Historico(String emailHistorico, String documento) {
        this.emailHistorico = emailHistorico;
        this.documento = documento;
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