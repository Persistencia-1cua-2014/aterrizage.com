package ar.edu.unq.persistencia1;


import ar.edu.unq.persistencia1.exceptions.NuevaPasswordInvalida;
import ar.edu.unq.persistencia1.exceptions.UsuarioNoExiste;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.exceptions.ValidacionException;
import ar.edu.unq.persistencia1.homes.RepositorioDeUsuarios;
import ar.edu.unq.persistencia1.mailer.Mailer;

public class Sistema {

    static String databaseName = "aterrizage";

    public void registrarUsuario(Usuario usuario) throws UsuarioYaExisteException {

        RepositorioDeUsuarios repo = this.getRepositorioDeUsuarios();
        repo.guardarUsuario(usuario);

    }

    public void validarCuenta(String codigo, String nombreDeUsuario) throws ValidacionException {
        RepositorioDeUsuarios repo = this.getRepositorioDeUsuarios();
        repo.validarCuenta(codigo, nombreDeUsuario);
    }

    public Usuario ingresarUsuario(String nombreDeUsuario, String password) throws UsuarioNoExiste {
        Usuario user = this.getRepositorioDeUsuarios().getUsuario(nombreDeUsuario, password);
        return user;
    }

    public void cambiarPassword(String userName, String pass, String nuevaPass) throws NuevaPasswordInvalida {
        if (this.getRepositorioDeUsuarios().existeUsuarioConPass(userName, pass) && pass.equals(nuevaPass)) {

            this.getRepositorioDeUsuarios().cambiarPassword(nuevaPass);
        } else {
            throw new NuevaPasswordInvalida();
        }

    }


    // help methods

    public String codigoDeValidacion(Usuario usuario) throws Exception {
        this.getRepositorioDeUsuarios().guardarCodigo(usuario, usuario.getNombreDeUsuario());
        return usuario.getNombreDeUsuario();

    }

    public Mailer getMailer() {
        Mailer mail = new Mailer();
        return mail;
    }

    public boolean existeCodigo(String codigo, String nombreDeUsuario) throws Exception {
        return this.getRepositorioDeUsuarios().existeCodigo(codigo, nombreDeUsuario);
    }

    public void sendMail(Usuario usuario) throws Exception {
        String codigoDeValidacion = this.codigoDeValidacion(usuario);
        Mailer mail = this.getMailer();
        mail.sendEmail(usuario.getEmail(), codigoDeValidacion);
    }

    public RepositorioDeUsuarios getRepositorioDeUsuarios() {
        return new RepositorioDeUsuarios(databaseName);

    }


}
