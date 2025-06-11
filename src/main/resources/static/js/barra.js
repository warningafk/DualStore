// barra.js - Funcionalidad de la barra lateral administrativa

document.addEventListener('DOMContentLoaded', function() {
    // Elementos del DOM
    const userToggle = document.getElementById('userToggle');
    const userDropdown = document.getElementById('userDropdown');
    const menu = document.querySelector('.menu');
    const barra = document.querySelector('.barra-lateral');
    const overlay = document.querySelector('.sidebar-overlay');

    // Toggle del dropdown del usuario
    if (userToggle && userDropdown) {
        userToggle.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            
            // Toggle del dropdown
            userDropdown.classList.toggle('show');
            userToggle.classList.toggle('active');
        });
    }

    // Cerrar dropdown al hacer click fuera
    document.addEventListener('click', function(e) {
        if (userDropdown && userToggle) {
            if (!userToggle.contains(e.target) && !userDropdown.contains(e.target)) {
                userDropdown.classList.remove('show');
                userToggle.classList.remove('active');
            }
        }
    });

    // Funcionalidad del menú móvil
    if (menu && barra && overlay) {
        menu.addEventListener('click', function() {
            barra.classList.toggle('show');
            overlay.classList.toggle('show');
        });

        overlay.addEventListener('click', function() {
            barra.classList.remove('show');
            overlay.classList.remove('show');
        });
    }

    // Cerrar dropdown con tecla ESC
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && userDropdown) {
            userDropdown.classList.remove('show');
            if (userToggle) {
                userToggle.classList.remove('active');
            }
        }
    });
});

// Funciones para las opciones del dropdown
function showUserInfo() {
    // Por ahora solo mostrar una alerta, puedes expandir esta funcionalidad
    alert('Información del perfil - Esta funcionalidad se puede expandir más adelante');
}

function showSettings() {
    // Por ahora solo mostrar una alerta, puedes expandir esta funcionalidad
    alert('Configuración - Esta funcionalidad se puede expandir más adelante');
}

// Función para confirmar logout
function confirmLogout() {
    return confirm('¿Está seguro que desea cerrar sesión?');
}
