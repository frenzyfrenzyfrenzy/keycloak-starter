export const getAllImageNames = () => {
    fetch('http://localhost:8080/api/image',
        {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('access_token')}`,
            }
        })
        .then(response => {
            if (response.status === 401) {

            }
        })
};