document.addEventListener('DOMContentLoaded', function() {
    const registrationForm = document.getElementById('registrationForm');
    const submitButton = document.getElementById('submitButton');
    const backButton = document.getElementById('backButton');
    const errorMessageElement = document.getElementById('errorMessage');

    let status = 'idle'; // idle, loading, success, error
    let errorMessage = '';

    // Слушатель кнопки "Назад"
    backButton.addEventListener('click', function() {
        window.history.back(); // Перенаправляем назад
    });

    // Функция для отправки формы регистрации
    registrationForm.addEventListener('submit', async function(event) {
        event.preventDefault();  // Отменяем стандартное поведение формы

        const username = document.getElementById('username').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password !== confirmPassword) {
            alert("Passwords do not match");
            return;
        }

        // Статус загрузки
        status = 'loading';
        errorMessage = '';
        submitButton.disabled = true; // Отключаем кнопку

        const formData = {
            username,
            email,
            password
        };

        try {
            const response = await fetch('http://localhost:8083/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || 'Error during registration');
            }

            status = 'success';
            alert("Registration successful!");
            // Очищаем поля формы
            document.getElementById('username').value = '';
            document.getElementById('email').value = '';
            document.getElementById('password').value = '';
            document.getElementById('confirmPassword').value = '';

        } catch (error) {
            console.error('Error:', error);
            status = 'error';
            errorMessage = error.message;
        } finally {
            // Обновляем статус кнопки и показываем ошибку, если она есть
            submitButton.disabled = false;
            if (status === 'error') {
                errorMessageElement.textContent = `Error: ${errorMessage}`;
            } else {
                errorMessageElement.textContent = ''; // Очистить сообщение об ошибке
            }
        }
    });
});
