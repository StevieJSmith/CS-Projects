const canvas = document.getElementById('canvas1');
const context = canvas.getContext('2d'); // access to 2d functions

// Set width and Height of canvas to avoid inconsistencies
const CANVAS_WIDTH = canvas.width = 800;
const CANVAS_HEIGHT = canvas.height = 700;

let gameFrame = 0;
let gameSpeed = 5; // default speed 

// create variables to access elements within HTML
const slider = document.getElementById('slider');
const showSpeed = document.getElementById('showGameSpeed');

slider.value = gameSpeed; // align values
showSpeed.innerHTML = gameSpeed; // display speed

slider.addEventListener('change', function(e){
    // on input change assign slider value to gameSpeed
    // and render the speed in the html of showGameSpeed
    gameSpeed = e.target.value;
    showSpeed.innerHTML = gameSpeed;
})

// create 5 images
const backgroundLayer1 = new Image();
const backgroundLayer2 = new Image();
const backgroundLayer3 = new Image();
const backgroundLayer4 = new Image();
const backgroundLayer5 = new Image();

// assign correct png to each image src
backgroundLayer1.src = 'layer-1.png';
backgroundLayer2.src = 'layer-2.png';
backgroundLayer3.src = 'layer-3.png';
backgroundLayer4.src = 'layer-4.png';
backgroundLayer5.src = 'layer-5.png';

window.addEventListener('load', function(){ // wait till page is loaded before running the following code
    class Layer { // blueprint for each layer
        constructor(image, speedModifier){
            this.x = 0;
            this.y = 0;
            this.width = 2400;
            this.height = 700;
            this.image = image;
            this.speedModifier = speedModifier;
            this.speed = gameSpeed * this.speedModifier;
        }
        update(){ // scroll speed change
            this.speed = gameSpeed * this.speedModifier;
            if(this.x <= -this.width){
                this.x = 0;
                // reset to 0 to push back the original image in the canvas
            }
            this.x = Math.floor(this.x - this.speed); // decrement values
            // this.x = gameFrame  * this.speed % this.width;
        }
        draw(){
            // display updated images on canvas
            context.drawImage(this.image, this.x, this.y, this.width, this.height);
            context.drawImage(this.image, this.x + this.width, this.y, this.width, this.height);
        }
    }
    
    // layer creation using Layer class blueprint (varying speeds!)
    const layer1 = new Layer(backgroundLayer1, 0.2);
    const layer2 = new Layer(backgroundLayer2, 0.4);
    const layer3 = new Layer(backgroundLayer3, 0.6);
    const layer4 = new Layer(backgroundLayer4, 0.8);
    const layer5 = new Layer(backgroundLayer5, 1);
    
    // array of objects containing our layers
    const layerObjects = [layer1, layer2, layer3, layer4, layer5]
    
    // clear the canvas on activation and loop through objects 
    // updating and drawing simitaniously!
    const animate = () => {
        context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        layerObjects.forEach(layer => {
            layer.update();
            layer.draw();
        })
        //gameFrame--;
        requestAnimationFrame(animate);
    };
    
    animate();
});