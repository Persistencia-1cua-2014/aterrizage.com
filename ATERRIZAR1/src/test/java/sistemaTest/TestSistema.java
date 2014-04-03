package sistemaTest;

import ar.edu.unq.persistencia1.Sistema;
import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.mailer.Mailer;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

/**
 * Created by lalo on 27/03/14.
 */
public class TestSistema{
    @Test
    public void testeSendEmail() throws Exception {
        Mailer mailer = mock(Mailer.class);
        Sistema sistema = mock(Sistema.class);
        stub(sistema.getMailer()).toReturn(mailer);
        Usuario usuario = new Usuario("lalo", "landa", "laloCura", "unEmail", new Date(),"12");
        sistema.sendMail(usuario);
        verify(mailer).sendEmail(usuario.getEmail(), sistema.codigoDeValidacion(usuario));
    }

}
