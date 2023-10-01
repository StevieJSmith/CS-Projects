const API_KEY = "" // Add your unique API Key from OpenAI to run the code!!!

const submitButton = document.querySelector('#submit')
const userInput = document.querySelector('input')
const imageHolder = document.querySelector('.image-holder')
const button = document.querySelector('button')

// To create variations of a provided image ---> https://api.openai.com/v1/images/variations

// To edit an existing image --->   https://api.openai.com/v1/images/edits

async function imageGenerate() { // function to send a message to chatGPT and receive a response!
    try {
        const response = await fetch("https://api.openai.com/v1/images/generations", {
            method: "POST",
            headers: {
                Authorization: `Bearer ${API_KEY}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
            prompt: userInput.value,
            n: 3,
            size: "1024x1024"
            })
        })
        const data = await response.json()
        console.log(data)


        data?.data.forEach(image => {
            const imagediv = document.createElement('div')
            imagediv.classList.add('generated-image')
            const imageElement = document.createElement('img')
            imageElement.setAttribute('src', image.url)
            imagediv.append(imageElement)
            imageHolder.append(imagediv)
        })
        userInput.value = ""
    }catch(error){
        console.error(error)
    }
}

function refreshPage() {
    window.location.reload();
}

submitButton.addEventListener('click', imageGenerate)
button.addEventListener('click', refreshPage)