// Імпорт CSS-файлу
import '../css/style.css';

// Основний JavaScript-код
document.addEventListener('DOMContentLoaded', () => {
    console.log('App is running!');

    // Перехватываем отправку формы для регистрации
    const registrationForm = document.querySelector('#registrationForm');
    if (registrationForm) {
        registrationForm.addEventListener('submit', (event) => {
            event.preventDefault();  // Отменяем стандартную отправку формы

            const formData = new FormData(registrationForm);  // Собираем данные формы

            // Отправляем данные формы через fetch
            fetch('/users/register', {
                method: 'POST',
                body: formData
            })
                .then((response) => {
                    if (response.ok) {
                        // Успешная регистрация, перенаправляем на страницу логина
                        window.location.href = '/users/login';
                    } else {
                        // Ошибка, например, если данные невалидны
                        alert('Registration failed. Please try again.');
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('An error occurred. Please try again.');
                });
        });
    }

    // Перехватываем отправку формы для логина
    const loginForm = document.querySelector('#loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', (event) => {
            event.preventDefault();  // Отменяем стандартную отправку формы

            const formData = new FormData(loginForm);  // Собираем данные формы

            // Отправляем данные формы через fetch
            fetch('/users/login', {
                method: 'POST',
                body: formData
            })
                .then((response) => {
                    if (response.ok) {
                        // Успешный вход, перенаправляем на страницу с пользователями
                        window.location.href = '/users/list';
                    } else {
                        // Ошибка, например, неверные данные
                        alert('Login failed. Please check your credentials.');
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('An error occurred. Please try again.');
                });
        });
    }

    // Обработчик клика по кнопке для перенаправления на другую страницу
    const button = document.querySelector('#myButton');
    if (button) {
        button.addEventListener('click', () => {
            alert('Button clicked!');
            // Пример перенаправления на страницу
            window.location.href = '/users/list';  // Перенаправляем на список пользователей
        });
    }

    // Динамическая загрузка списка пользователей на странице
    const usersTableBody = document.querySelector('#usersTableBody');
    if (usersTableBody) {
        fetch('/api/users')  // Предполагаем, что сервер возвращает список пользователей
            .then((response) => response.json())
            .then((users) => {
                users.forEach(user => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                    `;
                    usersTableBody.appendChild(tr);
                });
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('Failed to load users.');
            });
    }
});
