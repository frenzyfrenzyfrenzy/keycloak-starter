export const getAllImageNames = () => {
    fetch('/api/image',
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