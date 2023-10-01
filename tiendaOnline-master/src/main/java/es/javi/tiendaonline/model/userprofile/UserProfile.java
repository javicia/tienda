package es.javi.tiendaonline.model.userprofile;


import es.javi.tiendaonline.model.comentario.Comentario;
import es.javi.tiendaonline.model.pedido.Pedido;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class UserProfile {

	private Long userProfileId;
	private String loginName;
	private String encryptedPassword;
	private String firstName;
	private String lastName;
	private String email;
        private String dni;
        private long telefono;
        private String fechaNacimiento;
        private String tipoUsuario;
        private int numeroPuntos;
    
        private List<Comentario> comentarios;
        private List<Pedido> pedidos;

	public UserProfile() {
	}

	public UserProfile(String loginName, String encryptedPassword,
			String firstName, String lastName, String email, String 
                        dni, long telefono, String fechaNacimiento, String 
                        tipoUsuario, int numeroPuntos ) {

		/**
		 * NOTE: "userProfileId" *must* be left as "null" since its value is
		 * automatically generated.
		 */

		this.loginName = loginName;
		this.encryptedPassword = encryptedPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
                this.dni = dni;
                this.telefono = telefono;
                this.fechaNacimiento = fechaNacimiento;
                this.tipoUsuario = tipoUsuario;
                this.numeroPuntos = numeroPuntos;
                        
	}

	@Column(name = "usrId")
	@SequenceGenerator( // It only takes effect for
	name = "UserProfileIdGenerator", // databases providing identifier
	sequenceName = "UserProfileSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserProfileIdGenerator")
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "enPassword")
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

        public String getDni() {
          return dni;
        }

        public void setDni(String dni) {
          this.dni = dni;
        }

        public long getTelefono() {
          return telefono;
        }

        public void setTelefono(long telefono) {
          this.telefono = telefono;
        }

        public String getFechaNacimiento() {
          return fechaNacimiento;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
          this.fechaNacimiento = fechaNacimiento;
        }

        public String getTipoUsuario() {
          return tipoUsuario;
        }

        public void setTipoUsuario(String tipoUsuario) {
          this.tipoUsuario = tipoUsuario;
        }

        public int getNumeroPuntos() {
          return numeroPuntos;
        }

        public void setNumeroPuntos(int numeroPuntos) {
          this.numeroPuntos = numeroPuntos;
        }

	@Override
	public String toString() {
		return "Usuario [UsuarioId=" + userProfileId + ", loginName="
				+ loginName + ", encryptedPassword=" + encryptedPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", dni=" + dni + 
                                ", telefono=" + telefono + ", fechaNacimiento=" 
                                + fechaNacimiento +"]";
	}
        
  @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

  @OneToMany(mappedBy = "usuario")
  @Fetch(value = FetchMode.SUBSELECT)
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }



}
