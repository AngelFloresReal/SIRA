// Arreglo para almacenar los edificios y aulas
let buildings = ['Edificio 1', 'Edificio 2', 'Edificio 3'];
let rooms = {
    'Edificio 1': ['Aula 1', 'Aula 2', 'Aula 3'],
    'Edificio 2': ['Aula 1', 'Aula 2', 'Aula 3'],
    'Edificio 3': ['Aula 1', 'Aula 2', 'Aula 3']
};

// Obtener referencias al formulario y al calendario
const addEventForm = document.getElementById('add-event-form');
let calendar;

// Obtenemos los formularios y elementos necesarios
const addBuildingForm = document.getElementById('add-building-form');
const addRoomForm = document.getElementById('add-room-form');
const removeBuildingForm = document.getElementById('remove-docencia-form');
const buildingSelect = document.getElementById('building-select');
const roomSelect = document.getElementById('room-select');
const buildingSelectAddRoom = document.getElementById('building-select-add-room');

// Función para agregar un nuevo edificio
addBuildingForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const newBuilding = document.getElementById('new-building').value;
    buildings.push(newBuilding);
    updateBuildingSelect();
    document.getElementById('new-building').value = '';
});

// Funcion para eliminar un edificio
removeBuildingForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const buildingToRemove = document.getElementById('title_docencia_remove').value;
    const index = buildings.indexOf(buildingToRemove);
    if (index !== -1) {
        buildings.splice(index, 1);
        updateBuildingSelect();
        document.getElementById('title_docencia_remove').value = '';
    }
});

// Función para agregar una nueva aula
addRoomForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const newRoom = document.getElementById('new-room').value;
    const selectedBuilding = buildingSelectAddRoom.value;
    if (!rooms[selectedBuilding]) {
        rooms[selectedBuilding] = [];
    }
    rooms[selectedBuilding].push(newRoom);
    updateRoomSelect(selectedBuilding);
    document.getElementById('new-room').value = '';
});

// Función para actualizar la lista de edificios en el select
function updateBuildingSelect() {
    buildingSelect.innerHTML = '';
    buildingSelectAddRoom.innerHTML = '';
    buildings.forEach((building) => {
        const option = document.createElement('option');
        option.value = building;
        option.text = building;
        buildingSelect.appendChild(option);
        buildingSelectAddRoom.appendChild(option.cloneNode(true));
    });
}

// Función para actualizar la lista de aulas en el select
function updateRoomSelect(building) {
    roomSelect.innerHTML = '';
    const option = document.createElement('option');
    option.value = '';
    option.text = 'Seleccione una aula';
    roomSelect.appendChild(option);
    const roomsForBuilding = rooms[building];
    if (roomsForBuilding) {
        roomsForBuilding.forEach((room) => {
            const option = document.createElement('option');
            option.value = room;
            option.text = room;
            roomSelect.appendChild(option);
        });
    }
}



/*CALENDARIOS*/

// Agregar evento para crear un nuevo calendario cuando se selecciona un edificio o aula
buildingSelect.addEventListener('change', () => {
    const selectedBuilding = buildingSelect.value;
    updateRoomSelect(selectedBuilding);
    createCalendar(selectedBuilding, roomSelect.value);
});

roomSelect.addEventListener('change', () => {
    const selectedBuilding = buildingSelect.value;
    const selectedRoom = roomSelect.value;
    createCalendar(selectedBuilding, selectedRoom);
});





// Función para crear un nuevo calendario
function createCalendar(building, room) {


    const calendarContainer = document.getElementById('calendar-container');
    calendarContainer.innerHTML = '';

    calendar = new FullCalendar.Calendar(calendarContainer, {
        initialView: 'timeGridWeek',
        height: 'auto',
        allDaySlot: false,

        buttonText: {
            today: 'Hoy',
            month: 'Mes'
        },
        views: {
            timeGridWeek: {
                type: 'timeGridWeek',
                buttonText: 'Semana'
            },
            timeGridDay: {
                type: 'timeGridDay',
                buttonText: 'Día'
            },
        },
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        events: [
            // Cargar eventos desde una fuente de datos
            { title: 'Evento de prueba', start: '2024-07-25', end: '2024-07-26' },
            { title: 'Evento 2', start: '2023-03-05', end: '2023-03-07' }
        ],
        /*
        eventClick: function(info) {
          // Abrir modal para editar evento
          const btnAbrirModal = document.querySelector("#btn-abrir-modal");
          const modall = document.querySelector("#modal");
          const btnCerrarModal = document.querySelector("#btn-cerrar-modal");

          const eventTitle = document.getElementById('title');
          eventTitle.value = info.event.title;
          const eventStart = document.getElementById('start');
          eventStart.value = info.event.start;
          const eventEnd = document.getElementById('end');
          eventEnd.value = info.event.end;
          const modal = document.getElementById('modal');
          modal.showModal();

          // Eliminar evento
          const removeButton = document.getElementById('btn-cerrar-modal');
          removeButton.addEventListener('click', () => {
            modal.close();
          });

          // Actualizar evento
          const updateButton = document.getElementById('btn-cerrar-modal-update');
          updateButton.addEventListener('click', () => {
            info.event.setProp('title', eventTitle.value);
            info.event.setStart(eventStart.value);
            info.event.setEnd(eventEnd.value);
            modal.close();
          });
        }
          */
    });

    calendar.render();
    return calendar; // Devolver el objeto FullCalendar
    calendars[calendarId] = calendar;

}






// Inicializar selects y calendario
updateBuildingSelect();
updateRoomSelect(buildings[0]);
calendar = createCalendar(buildings[0], rooms[buildings[0]][0]);
const calendars = document.querySelectorAll('#calendar-container');


roomSelect.addEventListener('change', () => {
    const selectedRoom = roomSelect.value;
    if (selectedRoom === '') {
        // Ocultar todos los calendarios
        calendars.forEach((calendar) => {
            calendar.style.display = 'none';
        });
    }

    if (selectedRoom !== ''){
        calendars.forEach((calendar) => {
            calendar.style.display = 'block';
        });
    }
});



// Agregar evento de escucha al formulario de la ventana modal
addEventForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Evitar el envío del formulario

    // Obtener los valores del formulario
    const title = document.getElementById('title').value;
    const start = document.getElementById('start').value;
    const end = document.getElementById('end').value;

    // Crear un nuevo evento en el calendario
    calendar.addEvent({
        title: title,
        start: start,
        end: end
    });

    // Cerrar la ventana modal
    const modal = document.getElementById('modal');
    modal.close();


});








// Ventanas modales
const btnAbrirModal = document.querySelector("#btn-abrir-modal");
const modal = document.querySelector("#modal");
const btnCerrarModal = document.querySelector("#btn-cerrar-modal");

btnAbrirModal.addEventListener("click", ()=> {
    event.preventDefault();
    modal.showModal();

    btnCerrarModal.addEventListener("click", ()=> {
        modal.close();
    });
});

// Ventana agregar docencia
const btnAbrirModalAgregarDocencia = document.querySelector("#btn-abrir-modal-agregar-docencia");
const AgregarDocencia = document.querySelector("#modal-agregar-docencia");
const btnCerrarModalAgregarDocencia = document.querySelector("#btn-cerrar-modal-agregar-docencia");

btnAbrirModalAgregarDocencia.addEventListener("click", () => {
    event.preventDefault();
    AgregarDocencia.showModal();

    btnCerrarModalAgregarDocencia.addEventListener("click", () => {
        AgregarDocencia.close();
    });
});

// Ventana eliminar docencia
const btnAbrirModalEliminarDocencia = document.querySelector("#btn-abrir-modal-eliminar-docencia");
const EliminarDocencia = document.querySelector("#modal-eliminar-docencia");
const btnCerrarModalEliminarDocencia = document.querySelector("#btn-cerrar-modal-eliminar-docencia");

btnAbrirModalEliminarDocencia.addEventListener("click", () => {
    event.preventDefault();
    EliminarDocencia.showModal();

    btnCerrarModalEliminarDocencia.addEventListener("click", () => {
        EliminarDocencia.close();
    });
});

// Ventana agregar aula
const btnAbrirModalAgregarAula = document.querySelector("#btn-abrir-modal-agregar-aula");
const AgregarAula = document.querySelector("#modal-agregar-aula");
const btnCerrarModalAgregarAula = document.querySelector("#btn-cerrar-modal-agregar-aula");

btnAbrirModalAgregarAula.addEventListener("click",()=>{
    event.preventDefault();
    AgregarAula.showModal();

    btnCerrarModalAgregarAula.addEventListener("click",()=>{
        AgregarAula.close();
    })
})

// Ventana eliminar aula
const btnAbrirModalEliminarAula = document.querySelector("#btn-abrir-modal-eliminar-aula");
const EliminarAula = document.querySelector("#modal-eliminar-aula");
const btnCerrarModalEliminarAula = document.querySelector("#btn-cerrar-modal-eliminar-aula");

btnAbrirModalEliminarAula.addEventListener("click",()=>{
    event.preventDefault();
    EliminarAula.showModal();

    btnCerrarModalEliminarAula.addEventListener("click",()=>{
        EliminarAula.close();
    })
})

//ventana agregar usuario
const btnAbrirModalAgregarUsuario = document.querySelector("#btn-abrir-modal-agregar-usuario");
const AgregarUsuario = document.querySelector("#modal-agregar-usuario");
const btnCerrarModalAgregarUsuario = document.querySelector("#btn-cerrar-modal-agregar-usuario");

btnAbrirModalAgregarUsuario.addEventListener("click",()=>{
    event.preventDefault();
    AgregarUsuario.showModal();

    btnCerrarModalAgregarUsuario.addEventListener("click",()=>{
        AgregarUsuario.close();
    })
})
