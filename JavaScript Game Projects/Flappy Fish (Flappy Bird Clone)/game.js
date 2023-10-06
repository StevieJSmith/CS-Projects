document.addEventListener('DOMContentLoaded', () => {
    const screen = document.querySelector('.screen') // screen element selector
    const sky = document.querySelector('.sky')
    const ground = document.querySelector('.ground')
    const fish = document.querySelector('.fish')

    const scoreTotal = document.querySelector('#score')
    const resetButton = document.querySelector('#reset')
    const easyButton = document.querySelector('#easy')
    const normalButton = document.querySelector('#normal')
    const hardButton = document.querySelector('#hard')


    let fishLeft = 220 // left position
    let fishBottom  = 100
    let gap = 430 // space between top and bottom obstacle
    let score = 0 // total score of player

    let isGameOver = false
    let gravity = 0 // the weight of the fish
    let speed = 0 // speed of obstacles


    function flap() {
        if(fishBottom < 490) {
        fishBottom += 50 // increase height
        fish.style.bottom = fishBottom + 'px' // assign new height
        }
    }

    function control(e) {
        if(e.keyCode === 32) { // space bar
            flap()
        }
    }

    function createObstacle() {
        let obstacleBottom = Math.random() * 60 // generate a bottom position up to 60px
        let obstacleLeft = 500
        let obstacleAppear = 1000 + Math.random() * ((5000 - 1000)+1) // timer for when each new obstacle appears
        const obstacle = document.createElement('div')
        const topObstacle = document.createElement('div')

        if(!isGameOver){ // if game is not over create obstacles
            obstacle.classList.add('obstacle')
            topObstacle.classList.add('upsidedown-obstacle')

            obstacle.style.left = obstacleLeft + 'px'
            obstacle.style.bottom = obstacleBottom + 'px'
            topObstacle.style.left = obstacleLeft + 'px'
            topObstacle.style.bottom = obstacleBottom + gap + 'px'

            screen.appendChild(obstacle)
            screen.appendChild(topObstacle)
        }
        
        function obstaclePosition() { // moves as the game progresses
            obstacleLeft -= speed
            obstacle.style.left = obstacleLeft + 'px'
            topObstacle.style.left = obstacleLeft + 'px'

            if (obstacleLeft > 200 && obstacleLeft < 280 && fishLeft === 220 &&
                (fishBottom < obstacleBottom + 153 || fishBottom > obstacleBottom + gap - 200)||
                fishBottom < 0) {
                gameOver()
                clearInterval(positionTimer) // stop moving
            }

            if (obstacleLeft === -60){
                clearInterval(positionTimer)
                screen.removeChild(obstacle) 
                screen.removeChild(topObstacle)
                score++
            }
        }
        let positionTimer = setInterval(obstaclePosition, 20)
        if (!isGameOver){ // if game is not over continue to create
            setTimeout(createObstacle, obstacleAppear) 
            // once obstacle is off screen create another between 1-5 seconds
        }
        
    }

    function gameOver() {
        document.removeEventListener('keyup', control)
        clearInterval(gameTimer) // stop falling
        isGameOver = true // stop creating
        // remove ability to flap
        scoreTotal.innerHTML = "GAME OVER: " + score + " points!"


    }

    function refresh(){ // if user wants to try to again
        window.location.reload()
      }

    function startGame() {
        fishBottom -= gravity // continously remove gravity value from 'fishBottom' value
        fish.style.bottom = fishBottom + 'px'
        fish.style.left = fishLeft + 'px'

    }

    easyButton.onclick = function() {
        gravity = 3
        speed = 2
        document.addEventListener('keyup', control)
    }
    normalButton.onclick = function () {
        gravity = 3
        speed = 4
        document.addEventListener('keyup', control)
    }
    hardButton.onclick = function () {
        gravity = 3
        speed = 6
        document.addEventListener('keyup', control)
    }

    createObstacle()
    let gameTimer = setInterval(startGame, 20)

    resetButton.addEventListener('click', refresh)
    easyButton.addEventListener('click', startGame)
    normalButton.addEventListener('click', startGame)
    hardButton.addEventListener('click', startGame)







})