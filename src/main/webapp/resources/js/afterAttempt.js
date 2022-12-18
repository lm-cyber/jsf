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
