import axios, { AxiosError } from 'axios';

// axios.get('http://todo.kiarsy.com/health/live')
//     .then(function (response) {
//         console.log(response);
//     })
//     .catch(function (error) {
//         console.log(error);
//     });

axios.get('http://todo.kiarsy.com/todo/',{
    headers:{
        Authorization:'Bearer eyJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoiYWRtaW4gYWRtaW4iLCJpZCI6MSwic3ViIjoiYWRtaW4iLCJpYXQiOjE2NzUyNjk1NDcsImV4cCI6MTY3NTM1NTk0N30.oQ73cgBI7j4-1R6WQxFHNRdBe-ntDZ8zSSSh5RUKQHA858cCubWHCC0cfQKyajtHteBK0IMv-jv-Mu6XesiA-Q'
    }
})
    .then(function (response) {
        console.log(response);

    })
    .catch(function (error) {
        console.log(error);
        if (error instanceof AxiosError)
        {
            console.log(error.response?.data)
            console.log(error.response?.headers)

        }
    });

