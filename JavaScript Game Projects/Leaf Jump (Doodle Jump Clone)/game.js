document.addEventListener('DOMContentLoaded', () => { 
    // load JS after page has loaded

    // Variables

    const screen = document.querySelector('.screen') // screen class
    const leafer = document.createElement('div')
    const easyButton = document.querySelector('#easy')
    const normalButton = document.querySelector('#normal')
    const hardButton = document.querySelector('#hard')
    const scoreTotal = document.querySelector('#score')
    const retryButton = document.querySelector('#retry')

    

    let positionLeftleafer = 50
    let startPosition = 150
    let positionBottomleafer = startPosition
    let isGameOver = false
    numOfPlatforms = 5
    let platforms = [] // array for storing platforms being displayed
    let jumpTimer
    let fallTimer
    let leftTimer
    let rightTimer
    let isJumping = false
    let isMovingLeft = false
    let isMovingRight = false
    let score = 0
    let speed = 0

    // Class

    class Platform { // defining the platforms
        constructor(positionBottomPlatform) {
            this.bottom = positionBottomPlatform // evenly placed vertically
            this.left = Math.random() * 315 // varying horizontal placement
            this.display = document.createElement('div')

            const display = this.display
            display.classList.add('platform')
            display.style.left = this.left + 'px'
            display.style.bottom = this.bottom + 'px'
            screen.appendChild(display)
        }
    }


    // Functions
    
    function createLeafer() { // character creation
        leafer.classList.add('leafer')
        positionLeftLeafer = platforms[0].left
        positionBottomLeafer = platforms[0].bottom
        leafer.style.left = positionLeftLeafer + 'px'
        leafer.style.bottom = positionBottomLeafer + 'px'
        screen.appendChild(leafer) // place our character inside our game screen!
    }

    function createPlatforms() { // displaying the platforms
        for(let i = 0; i < numOfPlatforms; i++) {
            let platformDistance = 600 / numOfPlatforms
            let positionBottomPlatform = 100 + i * platformDistance
            let newPlatform = new Platform(positionBottomPlatform) 
            // place our platform inside our screen
            platforms.push(newPlatform)
        }
        console.log(platforms)
    }

    function platformMovement() {
        if (positionBottomLeafer > 200) { 
            // only move if character is high enough
            platforms.forEach(platform => {
                platform.bottom -= 4
                let display = platform.display
                display.style.bottom = platform.bottom + 'px'

                if(platform.bottom < 10) { // hits the border
                    let byePlatform = platforms[0].display // first element
                    byePlatform.classList.remove('platform')
                    platforms.shift() // remove first in array

                    let newPlatform = new Platform(580) // top of screen
                    platforms.push(newPlatform) // add platfrom to end of array
                }
            })
        }
    }

    function jump() {
        clearInterval(fallTimer) // stop falling
        isJumping = true
        jumpTimer = setInterval(function() {
            positionBottomLeafer += 20 // apply a jump distance
            leafer.style.bottom = positionBottomLeafer + 'px'
            if (positionBottomLeafer > startPosition + 200){
                fall() // if character reaches its max (200 above start)
                // then fall from that position
            }
        }, 30)
    }

    function fall() {
        clearInterval(jumpTimer) // stop jumping
        isJumping = false
        fallTimer = setInterval(function() {
            positionBottomLeafer -= speed // apply a fall distance
            leafer.style.bottom = positionBottomLeafer + 'px'
            if(positionBottomLeafer <= 0) {
                gameOver()
            }
            platforms.forEach(platform =>{
                if ((positionBottomLeafer >= platform.bottom) &&
                 (positionBottomLeafer <= platform.bottom + 15) &&
                  ((positionLeftLeafer + 60) >= platform.left) &&
                   ((positionLeftLeafer <= (platform.left + 85))) &&
                    !isJumping){
                    // check if poodler is in a platform space, whislt not jumping!
                    console.log('safely landed')
                    score++
                    startPosition = positionBottomLeafer
                    // override position after each successful jump
                    jump()
                }
            })
        }, 20)
    }

    function moveLeft() { // ability to move left
        if (isMovingRight) {
            clearInterval(rightTimer)
            isMovingRight = false
        }
        isMovingLeft = true
        leftTimer = setInterval(function() {
            if (positionLeftLeafer >= 0) {
                console.log('left')
                positionLeftLeafer -= 5 
                leafer.style.left = positionLeftLeafer + 'px'
            }
        }, 20)

    }

    function moveRight() { // ability to move right
        if (isMovingLeft) {
            clearInterval(leftTimer)
            isMovingLeft = false
        }
        isMovingRight = true
        rightTimer = setInterval(function() {
            if (positionLeftLeafer <= 340) {
                console.log('right')
                positionLeftLeafer += 5 
                leafer.style.left = positionLeftLeafer + 'px'
            }
        }, 20)
    }

    function moveUp() {
        isMovingLeft = false
        isMovingRight = false
        clearInterval(leftTimer)
        clearInterval(rightTimer)
        

    }

    function control(e) { // find out which key was pressed
        leafer.style.bottom = positionBottomLeafer + 'px'
        if (e.key === 'ArrowLeft') {
            moveLeft()
        }
        else if (e.key === 'ArrowRight') {
            moveRight()
        }
        else if (e.key === 'ArrowUp') {
            moveUp()
        }
    }

    

    function gameOver() { // character touches the bottom of the screen
        console.log('game over!!!')
        isGameOver = true // stop displaying game visuals 
        scoreTotal.innerHTML = "GAME OVER: " + score + " points!"
        screen.removeChild(leafer)
        
    }

    function refresh(){ // if user wants to try again to get a higher score or play another difficulty
        window.location.reload()
      }

    function startGame() {
        if(!isGameOver) // if game is not over display game visuals
            scoreTotal.innerHTML = ""
            createPlatforms()
            createLeafer()
            setInterval(platformMovement, 30) // continously call platformMovement function
            jump()
            // find the key that was pressed
            document.onkeydown = control
    
        }

        easyButton.onclick = function() { // character fall speed (difficulty settings)
            speed = 5
        }
        normalButton.onclick = function () {
            speed = 8
        }
        hardButton.onclick = function () {
            speed = 12
        }

        easyButton.addEventListener('click', startGame) // apply speed variable and start the game
        normalButton.addEventListener('click', startGame)
        hardButton.addEventListener('click', startGame)
        retryButton.addEventListener('click', refresh) // refresh the page resetting all variables


})