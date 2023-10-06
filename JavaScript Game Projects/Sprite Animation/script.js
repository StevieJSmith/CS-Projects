const canvas = document.getElementById('canvas1');
const context = canvas.getContext('2d');

const CANVAS_WIDTH = canvas.width = 600;
const CANVAS_HEIGHT = canvas.height = 600;

const playerImage = new Image();
playerImage.src = 'shadow_dog.png';
const spriteWidth = 575;
const spriteHeight = 523;

let playerState = 'idle'
const dropdown = document.getElementById('animations');
dropdown.addEventListener('change', function(e) {
    playerState = e.target.value;

})

let gameFrame = 0;
const staggerFrames = 5;
const spriteAnimations = [];

const animationStates = [
    {
        name: 'idle',
        frames: 7
    },
    {
        name: 'jump',
        frames: 7
    },
    {
        name: 'fall',
        frames: 7
    },
    {
        name: 'run',
        frames: 9
    },
    {
        name: 'dizzy',
        frames: 11
    },
    {
        name: 'sit',
        frames: 5
    },
    {
        name: 'roll',
        frames: 7
    },
    {
        name: 'bite',
        frames: 7
    },
    {
        name: 'ko',
        frames: 12
    },
    {
        name: 'damage',
        frames: 4
    }
    
];

// store all sprite frames x,y values inside the animationStates array!
animationStates.forEach((state, index) => {
     let frames = { // create a empty array for each index
        loc: [],
     }
     for (let i = 0; i < state.frames; i++){
        let positionX = i * spriteWidth;
        let positionY = index * spriteHeight;
        frames.loc.push({x: positionX, y: positionY})
     }
     spriteAnimations[state.name] = frames;

});

function animate() {
    context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    let position = Math.floor(gameFrame / staggerFrames) % spriteAnimations[playerState].loc.length; // change number depending on how many frames
    
    frameX = spriteAnimations[playerState].loc[position].x;
    frameY = spriteAnimations[playerState].loc[position].y;
    
    context.drawImage(playerImage, frameX, frameY, spriteWidth, spriteHeight,  0, 0, spriteWidth, spriteHeight);



    gameFrame++;
    requestAnimationFrame(animate);
};

animate();