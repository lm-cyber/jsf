function afterattempt(data) {
    if (data.status === 'success') {
        fetch('/web-faces/attempts')
            .then((data) => data.json())
            .then((data) => {
                points = data;
                rendergraph();
            });
    }
}

function rendertimestampsontable() {
    const els = document.getelementsbyclassname('timestamp');

    for (let i = 0; i < els.length; i++) {
        const el = els.item(i);
        const timestamp = parseint(el.innerhtml);
        if (!isnan(timestamp) && el.innerhtml.match('^[0-9]+$')) {
            el.innerhtml = new date(timestamp).tolocalestring();
        }
    }
}

setinterval(rendertimestampsontable, 10);