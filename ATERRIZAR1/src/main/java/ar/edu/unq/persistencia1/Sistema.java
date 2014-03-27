package ar.edu.unq.persistencia1;


import ar.edu.unq.persistencia1.homes.RepositorioDeUsuarios;
import ar.edu.unq.persistencia1.mailer.Mailer;

public class Sistema {

    static String databaseName = "aterrizage";

    public void registrarUsuario(Usuario usuario) throws Exception {

        RepositorioDeUsuarios repo = this.getRepositorioDeUsuarios();
        repo.guardarUsuario(usuario);

    }
    public void sendMail(Usuario usuario)throws Exception{
        String codigoDeValidacion = this.codigoDeValidacion(usuario);
        Mailer mail = this.getMailer();
        mail.sendEmail(usuario.getEmail(),codigoDeValidacion);
    }

    public RepositorioDeUsuarios getRepositorioDeUsuarios(){
        return new RepositorioDeUsuarios(databaseName);

    }
    public String codigoDeValidacion(Usuario usuario) throws Exception{
        this.getRepositorioDeUsuarios().guardarCodigo(usuario,usuario.getNombreDeUsuario());
        return usuario.getNombreDeUsuario();

    }
    public Mailer getMailer(){
        Mailer mail = new Mailer();
        return mail;
    }


   public void validarCuenta(String codigo){
           //TODO


   }

   public boolean existeCodigo(String codigo) throws Exception{
       return this.getRepositorioDeUsuarios().existeCodigo(codigo);
    }


}
