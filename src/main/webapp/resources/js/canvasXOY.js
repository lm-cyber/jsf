const canvas = document.getElementById("graph");
const FLOAT_REGEX = /^-?\d+(?:\.\d+)?$/;



const width = canvas.width;
const height = canvas.height;
const ctx = canvas.getContext("2d");

function runGrapher() {

    const FIGURE_COLOR = "#567efb99";

    function drawGraph() {
        ctx.font = "13px sans-serif";
        ctx.fillStyle = "#FFF";
        ctx.fillRect(0, 0, width, height);

        ctx.fillStyle = FIGURE_COLOR;
        // 1st quadrant triangle
        ctx.beginPath();
        ctx.moveTo(width / 2, height / 3);
        ctx.lineTo(width / 6 * 5, height / 2);
        ctx.lineTo(width / 2, height / 2);
        ctx.fill();

        ctx.beginPath();
        ctx.moveTo(0, height / 2);
        ctx.lineTo(width, height / 2);
        ctx.lineTo(width - 10, height / 2 - 10);
        ctx.moveTo(width, height / 2);
        ctx.lineTo(width - 10, height / 2 + 10);
        ctx.stroke();

        ctx.beginPath();
        ctx.moveTo(width / 2, height);
        ctx.lineTo(width / 2, 0);
        ctx.lineTo(width / 2 - 10, 10);
        ctx.moveTo(width / 2, 0);
        ctx.lineTo(width / 2 + 10, 10);
        ctx.stroke();

        // 2nd quadrant sector
        ctx.beginPath();
        ctx.arc(width / 2, height / 2, width / 6, Math.PI / 2, Math.PI, false);
        ctx.lineTo(width / 2, height / 2);
        ctx.fill();

        // 4th quadrant rectangl
        ctx.fillRect(width / 2, height / 2, width / 6 * 2, height / 6);


        ctx.fillStyle = "#000";
        const labels = ["-R", "-R/2", "0", "R/2", "R"];

        for (let i = 1; i < 6; i++) {
            ctx.beginPath();
            ctx.moveTo((i * width) / 6, height / 2 - 5);
            ctx.lineTo((i * width) / 6, height / 2 + 5);
            ctx.moveTo(width / 2 - 5, (i * height) / 6);
            ctx.lineTo(width / 2 + 5, (i * height) / 6);
            ctx.stroke();

            ctx.textAlign = "center";
            ctx.textBaseline = "bottom";
            ctx.fillText(labels[i - 1], (i * width) / 6, height / 2 - 7);

            ctx.textAlign = "left";
            ctx.textBaseline = "middle";
            ctx.fillText(labels[i - 1], width / 2 + 7, height - (i * height) / 6);
        }


        points.forEach((v) => {
            const r = getR();
            const x = v.x / r * width / 3 + width / 2;
            const y = -v.y / r * height / 3 + height / 2;

            ctx.fillStyle = (v.success ? '#2b6af3' : '#dc3545');
            ctx.beginPath();
            ctx.arc(x, y, 5, 0, Math.PI * 2);
            ctx.fill();
        });
    }


    return {
        drawGraph,
    };
}

function getR() {

    const docR = document.getElementById("j_idt19:r");
    const r = docR ? parseFloat(docR.value) : 1;
    if (r > 3) {
        docR.value = '3';
        return 3;
    }
    if (r < 1) {
        docR.value = '1';
        return 1;
    }
    return r;
}

const input =  document.getElementById("j_idt19:r");

function checkR(e) {
    const  r = e.target.value;
    if(!FLOAT_REGEX.test(r)){
        e.target.value = '1';
        return
    }
    if(r>3.){
        e.target.value='3';
    }
    if(r<1.){
        e.target.value = '1';
    }



}
input.addEventListener('change', checkR);

runGrapher().drawGraph();

function getMousePosition(e) {

    var mouseX = e.offsetX * canvas.width / canvas.clientWidth | 0;
    var mouseY = e.offsetY * canvas.height / canvas.clientHeight | 0;
    return {x: mouseX, y: mouseY};
}


canvas.addEventListener('click', (ev) => {

    const r = getR();
    // const x = Math.round((ev.offsetX / canvas.width - 0.5) * 3 * r * 100) / 100;
    // const y = Math.round((ev.offsetY / canvas.height - 0.5) * -3 * r * 100) / 100;

    let x = (ev.offsetX / width) * (3 * r) - (3 / 2) * r;
    let y = ((3 * r / 2 - (ev.offsetY / height * (3 * r))) * 10) / 10;

    fetch('/jsf-1.0-SNAPSHOT/sendCanvas?' + new URLSearchParams({xpoint: x, ypoint: y, rpoint: r}))
        .then((data) => data.json())
        .then((data) => {
            points = data;
            runGrapher().drawGraph();
        });
    ;
    document.getElementById('submit_ajax_table').click();


});

