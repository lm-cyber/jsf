function afterAttempt(data) {
    if (data.status === 'success') {
        fetch('/jsf-1.0-SNAPSHOT/attempts')
            .then((data) => data.json())
            .then((data) => {
                points = data;
                runGrapher().drawGraph();
            });
    }

}


function tableCreate() {
    const tablePlase = document.getElementById('new_table'),
        tbl = document.createElement('table');
    tablePlase.innerHTML=""
    tbl.style.width = '100%';
    tbl.style.border = '1px solid black';



    const thead = document.createElement('thead');
    thead.style.border ='1px solid black';

    tbl.appendChild(thead);
    const orderArrayHeader = ["num", "X", "Y", "R","Attempt time","Processing time","Result"];

    for (var i = 0; i < orderArrayHeader.length; i++) {
        th_cheld = document.createElement("th");
        thead.appendChild(th_cheld);
        th_cheld.style.border = '1px solid black';

        th_cheld.appendChild(document.createTextNode(orderArrayHeader[i]));
    }
    const tblBody = document.createElement("tbody");
    tblBody.style.border = '1px solid black';
    tbl.appendChild(tblBody);
    points.forEach((p) =>{
        var row = document.createElement('tr');
        arrOfP = Object.entries(p)
        arrOfP.forEach((d)=>{
            var cell = document.createElement('td');
            cell.style.border ='1px solid black';
            var cellText;
            if(typeof(d[1]) ==="boolean" ){
                cellText = document.createTextNode((d[1])?'HIT':'MISS');
            }
            else {
                cellText = document.createTextNode((d[1]).toString());
            }
            cell.appendChild(cellText);
            row.appendChild(cell);
        });
        tblBody.appendChild(row);
    });


    tablePlase.appendChild(tbl);
}


function renderTimestampsOnTable() {
    const els = document.getElementsByClassName('timestamp');

    for (let i = 0; i < els.length; i++) {
        const el = els.item(i);
        const timestamp = parseInt(el.innerHTML);
        if (!isNaN(timestamp) && el.innerHTML.match('^[0-9]+$')) {
            el.innerHTML = new Date(timestamp).toLocaleString();
        }
    }
}

setInterval(renderTimestampsOnTable, 1);
