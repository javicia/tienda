package es.javi.tiendaonline.model.userservice;

public class UserProfileDetails {

	private String firstName;
	private String lastName;
	private String email;
        private String dni;
        private long telefono;
        private String fechaNacimiento;
        private String tipoUsuario;
        private int numeroPuntos;

	public UserProfileDetails(String firstName, String lastName, String email, 
                String dni, long telefono, String fechaNacimiento, 
                String tipoUsuario, int numeroPuntos)
        {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
                this.dni = dni;
                this.telefono = telefono;
                this.fechaNacimiento = fechaNacimiento;
                this.tipoUsuario = tipoUsuario;
                this.numeroPuntos = numeroPuntos;
                
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

        public String getDni() {
          return dni;
        }

        public long getTelefono() {
          return telefono;
        }

        public String getFechaNacimiento() {
          return fechaNacimiento;
        }

        public String getTipoUsuario() {
          return tipoUsuario;
        }

  public int getNumeroPuntos() {
    return numeroPuntos;
  }

}
