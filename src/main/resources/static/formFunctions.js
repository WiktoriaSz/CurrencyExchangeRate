function doCheck()
{
    var changed= false;

    if(document.getElementById("sel1").value!=null && document.getElementById("sel2").value==null)
    {
        changed=true;
    }
    if(changed){
        alert("submitting form : "+changed);
        document.forms[0].submit;
    }
    else{
        alert("Not submit form : "+changed);
        return false;
    }
}