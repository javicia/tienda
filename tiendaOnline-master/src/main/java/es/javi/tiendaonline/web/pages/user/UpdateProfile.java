package es.javi.tiendaonline.web.pages.user;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.javi.tiendaonline.model.userprofile.UserProfile;
import es.javi.tiendaonline.model.userservice.UserProfileDetails;
import es.javi.tiendaonline.model.userservice.UserService;
import es.javi.tiendaonline.web.pages.Index;
import es.javi.tiendaonline.web.services.AuthenticationPolicy;
import es.javi.tiendaonline.web.services.AuthenticationPolicyType;
import es.javi.tiendaonline.web.util.UserSession;
import es.udc.pojo.modelutil.exceptions.InstanceNotFoundException;

@AuthenticationPolicy(AuthenticationPolicyType.AUTHENTICATED_USERS)

public class UpdateProfile {

    @Property
    private String firstName;

    @Property
    private String lastName;

    @Property
    private String email;
    
    @Property
    private String dni;
    
    @Property
    private long telefono;
    
    @Property
    private String fechaNacimiento;
    
    @Property
    private String tipoUsuario;
 
    int numeroPuntos;

    @SessionState(create=false)
    private UserSession userSession;

    @Inject
    private UserService userService;

    void onPrepareForRender() throws InstanceNotFoundException {

        UserProfile userProfile;

        userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
        firstName = userProfile.getFirstName();
        lastName = userProfile.getLastName();
        email = userProfile.getEmail();
        dni = userProfile.getDni();
        telefono = userProfile.getTelefono();
        fechaNacimiento = userProfile.getFechaNacimiento();
       

    }

    Object onSuccess() throws InstanceNotFoundException {

        userService.updateUserProfileDetails(
                userSession.getUserProfileId(), new UserProfileDetails(
                        firstName, lastName, email, dni, telefono, 
                fechaNacimiento,tipoUsuario, numeroPuntos));
        userSession.setFirstName(firstName);
        return Index.class;

    }

  public int getNumeroPuntos() throws InstanceNotFoundException {
     UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    return userProfile.getNumeroPuntos();
  }

  public void setNumeroPuntos(int numeroPuntos) throws InstanceNotFoundException {
     UserProfile  userProfile = userService.findUserProfile(userSession
                .getUserProfileId());
    this.numeroPuntos = userProfile.getNumeroPuntos();
  }

}