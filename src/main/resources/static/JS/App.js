
function validarEdad() {
            var edad = document.getElementById("edad").value;

            if (edad < 18) {
                alert("La edad debe ser mayor o igual que 18.");
                return false;
            }

            return true;
        }
        
function validarFechas() {
        var fechaInicio = new Date(document.getElementById('inicio').value);
        var fechaFinal = new Date(document.getElementById('fin').value);

        if (fechaInicio >= fechaFinal) {
            alert('La fecha de inicio debe ser menor que la fecha final.');
            return false; // Evita enviar el formulario si la validación falla
        }

        return true; // Continúa enviando el formulario si la validación es exitosa
    }
	
function validarFormulario() {
    var nombreCompeticion = document.getElementById('nameC').value;

    
    if (nombreYaExiste) {
        alert("El nombre de la competición ya existe. Por favor, elige otro nombre.");
        return false; 
    }

    return true;
}




 window.onload = function() {
        var selectElement = document.getElementById('jugadoresV');
        var options = selectElement.options;
        
        for (var i = 0; i < options.length; i++) {
            options[i].selected = true;
        }
    };


