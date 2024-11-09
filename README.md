Inicialización del Proyecto
	Se crea el proyecto con un commit inicial. Se configura la estructura básica de carpetas y dependencias para iniciar el desarrollo.

Añadido de Logo y Temas de Interfaz
	Se incorpora un logo y se aplica el tema FlatLafDark para modernizar la apariencia de la interfaz de usuario. También se implementa la funcionalidad de hipervínculos en la interfaz gráfica.

Desarrollo de la Autenticación de Usuario
	Se añade un cuadro de diálogo de inicio de sesión inicial sin conexión a base de datos.
	La autenticación se conecta a una base de datos SQL. La lógica de autenticación se implementa en Logica.java, habilitando la verificación de credenciales de usuario.

Visualización de Listas de Usuarios y Reseñas
	Se añaden tablas para mostrar y gestionar las listas de usuarios y reseñas en la interfaz de usuario.

Implementación de Funciones de Gestión de Reseñas
	Se establece la lógica para verificar si las reseñas han sido revisadas, lo cual se añade en Logica.java. Este cambio prepara la interfaz para gestionar la verificación del estado de las reseñas.
	Se agregan botones en la interfaz gráfica para las acciones de agregar, modificar y eliminar reseñas.
	 Se completa la implementación de agregar, modificar y eliminar reseñas en la interfaz principal, MainFrame.java, garantizando que estas funcionalidades estén operativas.

Soporte para Video
	Se inicia el soporte para la carga de videos, y después de múltiples ajustes, el reproductor de videos queda finalmente funcional. Este desarrollo tomó varios intentos y ajustes (en 298b5b3 y 76f9bcd), hasta que el componente de reproducción se integró completamente.

Finalización y Correcciones
	Se realizan las correcciones finales en las funciones de agregar, modificar y eliminar reseñas, asegurando su correcto funcionamiento dentro de la interfaz de usuario.

Errores durante la creaccion del proyecto: 
Al intentar usar el EmbededMediaPlayerComponent tuve algunos probelmas, ya que, primero, no tenia el vlc instalado, luego daba errores porque tenia partes del codigo mal, y al final no se mostraba
al usar el .setVisible(true), porque lo agregaba despues de inicializar los componentes y no lo redibujaba. Lo solucione con el metodo: 

    private void prepareVideoPlayer(){
        videoPlayerPanel.setLayout(new BorderLayout());
        videoPlayerPanel.add(mediaPlayerComponente, BorderLayout.CENTER); // Agrega mediaPlayerComponente al panel
        videoPlayerPanel.revalidate(); // Refresca el panel para que muestre el reproductor
        videoPlayerPanel.repaint();
    }


A mitad del proyecto me di cuenta de que la mitad de funciones estaban mal hechas, ya que solicitaban valores a la base de datos con informacion erronea o insufuciente
Tambien con algunas sentencias sql en vez de usar: 
			     |
			     v
______________________________________________________________________________________________
	String sql = "SELECT i.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
                     	"FROM Intents i " +
                     	"JOIN Exercicis e ON i.idExercici = e.Id " +
                     	"where IdUsuari = ? " +
                     	"and idExercici = ?";
______________________________________________________________________________________________

Usaba			     |
			     v
______________________________________________________________________________________________
	String sql = "SELECT e.id, e.Descripcio, Timestamp_inici, IdUsuari, Videofile " +
                     	"FROM Intents i " +
                     	"JOIN Exercicis e ON i.idExercici = e.Id " +
                     	"where IdUsuari = ? " +
                     	"and idExercici = ?";
______________________________________________________________________________________________

Y no encontraba los intentos bien
