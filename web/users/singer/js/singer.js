onload=function change() {
    var qb = document.getElementById("qb");
    var zg = document.getElementById("zg");
    var om = document.getElementById("om");
    var hg = document.getElementById("hg");
    var rb = document.getElementById("rb");
    var qt = document.getElementById("qt");
    var all = document.getElementById("all");
    var china = document.getElementById("china");
    var euramerica = document.getElementById("euramerica");
    var korea = document.getElementById("korea");
    var japan = document.getElementById("japan");
    var es = document.getElementById("es");

    qb.onclick=function () {
        all.style.display="block";
        china.style.display="none";
        euramerica.style.display="none";
        korea.style.display="none";
        japan.style.display="none";
        es.style.display="none";
    };

    zg.onclick=function () {
        all.style.display="none";
        china.style.display="block";
        euramerica.style.display="none";
        korea.style.display="none";
        japan.style.display="none";
        es.style.display="none";
    };
    om.onclick=function () {
        all.style.display="none";
        china.style.display="none";
        euramerica.style.display="block";
        korea.style.display="none";
        japan.style.display="none";
        es.style.display="none";
    };
    hg.onclick=function () {
        all.style.display="none";
        china.style.display="none";
        euramerica.style.display="none";
        korea.style.display="block";
        japan.style.display="none";
        es.style.display="none";
    };
    rb.onclick=function () {
        all.style.display="none";
        china.style.display="none";
        euramerica.style.display="none";
        korea.style.display="none";
        japan.style.display="block";
        es.style.display="none";
    };
    qt.onclick=function () {
        all.style.display="none";
        china.style.display="none";
        euramerica.style.display="none";
        korea.style.display="none";
        japan.style.display="none";
        es.style.display="block";
    };
};