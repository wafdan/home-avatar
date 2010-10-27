// JavaScript Document
function tes() {
	var coba = "2009-19-09";
	var aa = coba.substr(0, 4);
	var bb = coba.substr(5, 2);
	var cc = coba.substr(8, 2);
	var dd = coba.search("-");
	//document.getElementById("teks").value = "fdsadsfdsf";
	document.write("Tanggal : " + aa + " " + bb + " " + cc + "on" + dd);
	return true;
}

function validate_nama()
{
	var nama = document.getElementById('nama').value;
	var patt1 = new RegExp("[^a-z A-Z]","g");
	var res = nama.search(patt1);
	
	if (nama.length < 5 || res > -1) {
		document.getElementById('dnama').style.visibility = "visible";
		document.getElementById('dnama').style.display = "block";
		return false;
	} else {
		document.getElementById('dnama').style.visibility= "hidden";
		document.getElementById('dnama').style.display = "none";
		return true;
	}
}

function validate_username()
{
	var username = document.getElementById('username').value;
	var patt1 = new RegExp("[^a-zA-Z0-9._]","g");
	var res = username.search(patt1);
	
	if (username.length < 5 || res > -1) {
		document.getElementById('dusername').style.visibility = "visible";
		document.getElementById('dusername').style.display = "block";
		return false;
	} else {
		document.getElementById('dusername').style.visibility= "hidden";
		document.getElementById('dusername').style.display = "none";
		return true;
	}
}

function validate_password()
{
	var password = document.getElementById('password').value;
	
	if (password.length < 6) {
		document.getElementById('dpassword').style.visibility = "visible";
		document.getElementById('dpassword').style.display = "block";
		return false;
	} else {
		document.getElementById('dpassword').style.visibility= "hidden";
		document.getElementById('dpassword').style.display = "none";
		return true;
	}
}

function validate_password2()
{
	var password1 = document.getElementById('password').value;
	var password2 = document.getElementById('password2').value;
	
	if (password2 != password1) {
		document.getElementById('dpassword2').style.visibility = "visible";
		document.getElementById('dpassword2').style.display = "block";
		return false;
	} else {
		document.getElementById('dpassword2').style.visibility= "hidden";
		document.getElementById('dpassword2').style.display = "none";
		return true;
	}
}

function validate_tanggal_lahir()
{
	var tanggal_lahir = document.getElementById('tanggal_lahir').value;
    var ret = true;
	var patt1 = new RegExp("[^0-9-]","g");
	var res = tanggal_lahir.search(patt1);
	
    var yy;
    var mm;
    var dd;
    var first_strip = tanggal_lahir.search("-");

    if(tanggal_lahir.length != 10) {
        ret = false;
    }
    else {
		if (first_strip == 4) {
			yy = tanggal_lahir.substr(0,4);
			
			var sisa = tanggal_lahir.substr(5,5);
			var second_strip = sisa.search("-");
			if(second_strip == 2) {
				mm = sisa.substr(0,2);
				dd = sisa.substr(3,2);
			}
			else {
				ret = false;
			}
		}
		else {
			ret = false;
		}
    }

    if((mm == 1)||(mm == 3)||(mm == 5)||(mm == 7)||(mm == 8)||(mm == 10)||(mm == 12)) {
        if((dd > 31)||(dd < 1)) {
          ret = false;
        }
    }
    else if((mm == 4)||(mm == 6)||(mm == 9)||(mm == 11)) {
        if((dd > 30)||(dd < 1)) {
          ret = false;
        }
    }
    else if(mm == 2) {
        if((yy % 4)== 0) {
            if((dd > 29)||(dd < 1)) {
              ret = false;
            }
        }
        else {
            if((dd > 28)||(dd < 1)) {
              ret = false;
            }
        }
    }
    else
    {
        ret = false;
    }
	if (res > -1) {
		ret = false;
	}
	if (ret == false) {
		document.getElementById('dtanggal_lahir').style.visibility = "visible";
		document.getElementById('dtanggal_lahir').style.display = "block";
	} else {
		document.getElementById('dtanggal_lahir').style.visibility = "hidden";
		document.getElementById('dtanggal_lahir').style.display = "none";
	}
    return ret;
}

function validate_foto()
{
	var foto = document.getElementById("foto").value;
	var dotpos = foto.lastIndexOf(".");
    var ekstensi = foto.substring(dotpos+1, foto.length);

	if ((ekstensi == "jpg")||(ekstensi == "jpeg")||(ekstensi == "gif")||(ekstensi == "tif")||(ekstensi == "png")) {
		document.getElementById('dfoto').style.visibility = "hidden";
		document.getElementById('dfoto').style.display = "none";
		return true;
	} else if (foto.length == 0) { 
		document.getElementById('dfoto').style.visibility = "visible";
		document.getElementById('dfoto').style.display = "block";
		return false; 
	} else {
		document.getElementById('dfoto').style.visibility = "visible";
		document.getElementById('dfoto').style.display = "block";
		return false;
	}
}

function validate_no_telp() {
	var no_telp = document.getElementById('no_telp').value;
	var patt1 = new RegExp("[^+0-9]","g");
	var res = no_telp.search(patt1);/*
	var pluspos = no_telp.indexof("+");
	if (pluspos > -1) {
		if (pluspos != 0) {
			document.getElementById('dno_telp').style.visibility = "visible";
			document.getElementById('dno_telp').style.display = "block";
			return false;
		}
	}*/
	if (no_telp.length < 7 || res > -1) {
		document.getElementById('dno_telp').style.visibility = "visible";
		document.getElementById('dno_telp').style.display = "block";
		return false;
	} else {
		document.getElementById('dno_telp').style.visibility= "hidden";
		document.getElementById('dno_telp').style.display = "none";
		return true;
	}
}

function validate_email()
{
	var email = document.getElementById('email').value;
	var patt1 = new RegExp("[^a-zA-Z0-9-.@]","g");
	var res = email.search(patt1);
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");
	var afterat = dotpos - atpos;
	var afterdot = email.length - dotpos;
	if (afterdot < 3) {
		document.getElementById('demail').style.visibility = "visible";
		document.getElementById('demail').style.display = "block";
		return false;
	}
    else if (email.length < 5) {
        document.getElementById('demail').style.visibility = "visible";
		document.getElementById('demail').style.display = "block";
		return false;
    }
	else if (res > -1) {
		document.getElementById('demail').style.visibility = "visible";
		document.getElementById('demail').style.display = "block";
		return false;
	}
	else if (atpos < 1 || afterat < 2) {
		document.getElementById('demail').style.visibility = "visible";
		document.getElementById('demail').style.display = "block";
		return false;
    }
	else {
		document.getElementById('demail').style.visibility= "hidden";
		document.getElementById('demail').style.display = "none";
		return true;
	}
}

function validate_alamat()
{
	var alamat = document.getElementById('alamat').value;
	
	if (alamat.length < 10 ) {
		document.getElementById('dalamat').style.visibility = "visible";
		document.getElementById('dalamat').style.display = "block";
		return false;
	} else {
		document.getElementById('dalamat').style.visibility= "hidden";
		document.getElementById('dalamat').style.display = "none";
		return true;
	}
}

function form_reset()
{
	document.getElementById("dnama").style.visibility = "hidden";				
	document.getElementById("dusername").style.visibility = "hidden";
	document.getElementById("dpassword").style.visibility = "hidden";
	document.getElementById("dpassword2").style.visibility = "hidden";
	document.getElementById("dtanggal_lahir").style.visibility = "hidden";
	document.getElementById("dfoto").style.visibility="hidden";
	document.getElementById("dno_telp").style.visibility="hidden";
	document.getElementById("demail").style.visibility="hidden";
	document.getElementById("djenis_kelamin").style.visibility="hidden";
	document.getElementById("dprovinsi").style.visibility="hidden";
	document.getElementById("dkota").style.visibility="hidden";				
	document.getElementById("dalamat").style.visibility="hidden";
	
	document.getElementById("dnama").style.display = "none";	
	document.getElementById("dusername").style.display = "none";
	document.getElementById("dpassword").style.display = "none";
	document.getElementById("dpassword2").style.display = "none";
	document.getElementById("dtanggal_lahir").style.display = "none";
	document.getElementById("dfoto").style.display = "none";
	document.getElementById("dno_telp").style.display = "none";
	document.getElementById("demail").style.display = "none";
	document.getElementById("djenis_kelamin").style.display = "none";
	document.getElementById("dprovinsi").style.display = "none";
	document.getElementById("dkota").style.display = "none";			
	document.getElementById("dalamat").style.display = "none";
	
	return true;
}

function form_validate() {
	if (
		validate_name() && validate_username() && validate_password() && validate_password2() &&
		validate_tanggal_lahir() && validate_foto() && validate_no_telp() && validate_email &&
		validate_alamat()
		) {
		alert("Pendaftaran berhasil!");
	}
}

/*
var nameregex = /^([a-z A-Z]+)$/;
var kotaregex = /^([a-z A-Z]+)$/;
var telponregex = /^([0-9]{9})/
var alamatregex = /^([0-9A-Za-z .\/-]+)$/;
var passwordregex = /^([0-9A-Za-z]+)$/;
var emailregex = /^([\w]+)(.[\w]+)*@([\w]+)(.[\w]{2,3}){4,5}$/;
var tanggalregex= /^([0-9]){2}(\/|-){1}([0-9]){2}(\/|-)([0-9]){4}$/;
var extensionregex = /.(jpg|png|bmp|gif)/;

var statusNama="";
var statusTelpon="";
var statusAlamat="";
var statusKota="";
var statusProvinsi="";
var statusPassword="";		
var statusKonfirmasi="";
var statusEmail="";
var statusTgl="";
var statusExtension="";

function tgl_lahir_validate()
{
	var tglr = document.getElementById("tgllahir").value;
	
	if (tglr!="")
	{
		if(tanggalregex.test(tglr)!=true)
		{
			document.getElementById("ntgl").style.visibility="visible";
			document.getElementById("tgl").style.visibility="hidden";
			statusTgl="false";
			return false;
		}
		else{
			statusTgl="true";
			document.getElementById("ntgl").style.visibility="hidden";
			document.getElementById("tgl").style.visibility="hidden";
			return true;
		}
	}
	else
	{return false;}
}


function name_validate()
{
	var name=document.getElementById("nama").value;
	
	if(name!="") //jika tidak kosong,maka periksa dengan regex
	{
		if (nameregex.test(name)!=true){
			statusNama="false";
			document.getElementById("ns").style.visibility="visible";
		//	document.getElementById("nama").style.borderColor="red";
			document.getElementById("sname").style.visibility="hidden";				

			return false;
		}
		else{
			statusNama="true";
			document.getElementById("ns").style.visibility="hidden";
			document.getElementById("sname").style.visibility="hidden";				

		return true;
		}
	}
	else {return false;}
	
}

function telpon_validate()
{
	var telp=document.getElementById("telephone").value;
	if (telp!="")
		{
		if (telponregex.test(telp)!=true){
			statusTelpon="false";
			document.getElementById("nt").style.visibility="visible";
			document.getElementById("sphone").style.visibility="hidden";
				
			return false;
		}
		else{
			statusTelpon="true";
			document.getElementById("nt").style.visibility="hidden";
			document.getElementById("sphone").style.visibility="hidden";
				
			return true;
		}
	}
	else {return false;}
}

function alamat_validate()
{
	var almt=document.getElementById("alamat").value;
	if(almt!="")
	{
		if (alamatregex.test(almt)!=true){
			statusAlamat="false";
			document.getElementById("na").style.visibility="visible";
			document.getElementById("saddress").style.visibility="hidden";
			return false;
		}
		else{
			statusAlamat="true";
			document.getElementById("na").style.visibility="hidden";
			
			document.getElementById("saddress").style.visibility="hidden";
			return true;
		}
	}
	else {return false;}
}

function kota_validate()
{
	var kt = document.getElementById("kota").value;
	if(kt!="")
	{
		if (kotaregex.test(kt)!=true){
			statusKota="false";
			document.getElementById("nk").style.visibility="visible";
			document.getElementById("scity").style.visibility="hidden";
			return false;
		}
		else{
			statusKota="true";
			document.getElementById("nk").style.visibility="hidden";
			document.getElementById("scity").style.visibility="hidden";
			return true;
		}
	}
	else {return false;}
}

function provinsi_validate()
{
	var prov = document.getElementById("provinsi").value;
	if(prov!="")
	{
		if (nameregex.test(prov)!=true){
			statusProvinsi="false";
			document.getElementById("np").style.visibility="visible";
			document.getElementById("sprovince").style.visibility="hidden";
			return false;
		}
		else{
			statusProvinsi="true";
			document.getElementByI("np").style.visibility="hidden";
			document.getElementById("sprovince").style.visibility="hidden";
			return true;
		}
	}
	else {return false;}
}

function password_validate() //pemeriksaan untk password hanya bila kosong
{
	var pass = document.getElementById("pass1").value;
	if(pass!=""){
		statusPassword="true";
		document.getElementById("npass1").style.visibility="hidden";
		return true;
	}
	else {statusPassword="false";
		  document.getElementById("npass1").style.visibility="hidden";
	return false;}
}

function konfirmasi_validate() //pemeriksaan hanya pada kesamaan password, tidak pada karakter2nya
{
	if(document.getElementById("pass2").value!="")
	{
		if (document.getElementById("pass1").value!=document.getElementById("pass2").value){
			statusKonfirmasi="false";
			document.getElementById("npass2").style.visibility="visible";	
			return false;
		}
		else{
			statusKonfirmasi="true";
			document.getElementById("npass2").style.visibility="hidden";
			return true;
		}
	}
	else {return false;}
}

function email_validate()
{
	var eml=document.getElementById("email").value;
	if(eml!="")
	{
		if (emailregex.test(eml)!=true){
			statusEmail="false";
			document.getElementById("ne").style.visibility="visible";
			document.getElementById("nemail").style.visibility="hidden";
			return false;
		}
		else{
			statusEmail="true";
			document.getElementById("ne").style.visibility="hidden";
			document.getElementById("nemail").style.visibility="hidden";
			return true;
		}
	}
	else {return false;}
}

function foto_validate()
{
	var fot=document.getElementById("foto").value;
	if(fot!="")
	{
		if (extensionregex.test(fot)!=true){
			statusExtension="false";
			document.getElementById("sphoto").style.visibility="hidden";
			return false;
		}
		else{
			statusExtension="true";
			document.getElementById("sphoto").style.visibility="hidden";
			return true;
		}
	}
	else {return false;}
}

function form_validate()
{
	if(name_validate()==true && telpon_validate()==true
		&& alamat_validate()==true && (statusKota=="true" || statusKota=="")
		&& (statusProvinsi=="true"||statusKota=="true") && password_validate()==true
		&& konfirmasi_validate()==true && email_validate()==true
		&& (statusExtension=="true" || statusExtension==""))
		{
			
			document.getElementById("sname").style.visibility="hidden";				
			document.getElementById("sphone").style.visibility="hidden";
			document.getElementById("saddress").style.visibility="hidden";
			document.getElementById("scity").style.visibility="hidden";
			document.getElementById("sprovince").style.visibility="hidden";
			document.getElementById("npass1").style.visibility="hidden";
			document.getElementById("npass2").style.visibility="hidden";
			document.getElementById("nemail").style.visibility="hidden";
			document.getElementById("sphoto").style.visibility="hidden";
			document.getElementById("tgl").style.visibility="hidden";
			document.getElementById("sname").style.visibility="hidden";				
			document.getElementById("ntgl").style.visibility="hidden";
			document.getElementById("ns").style.visibility="hidden";				
			document.getElementById("nt").style.visibility="hidden";
			document.getElementById("na").style.visibility="hidden";
			document.getElementById("nk").style.visibility="hidden";
			document.getElementById("np").style.visibility="hidden";
			document.getElementById("ne").style.visibility="hidden";
			alert("Registrasi berhasil!");
			return true;
		}
	else 
	{	

		if (name_validate()!=true)
		{
			document.getElementById("sname").style.visibility="visible";				
		}
		if (telpon_validate()!=true)
		{
			document.getElementById("sphone").style.visibility="visible";
		}
		if (alamat_validate()!=true)
		{
			document.getElementById("saddress").style.visibility="visible";
		}
		if(statusKota!="true" && statusKota!="")
		{
			document.getElementById("scity").style.visibility="visible";
		}
		if(statusProvinsi!="true" && statusProvinsi!="")
		{

			document.getElementById("sprovince").style.visibility="visible";
		}
		if(password_validate()!=true)
		{
			document.getElementById("npass1").style.visibility="visible";
		}
		if(konfirmasi_validate()!=true)
		{
			document.getElementById("npass2").style.visibility="visible";
		}
		if(email_validate()!=true)
		{
			document.getElementById("nemail").style.visibility="visible";
		}
		if(statusExtension!="true" && statusExtension!="")
		{
			document.getElementById("sphoto").style.visibility="visible";
		}
		if(tgl_lahir_validate()!=true)
		{
				document.getElementById("tgl").style.visibility="visible";
		}
			document.getElementById("ntgl").style.visibility="hidden";
			document.getElementById("ns").style.visibility="hidden";				
			document.getElementById("nt").style.visibility="hidden";
			document.getElementById("na").style.visibility="hidden";
			document.getElementById("nk").style.visibility="hidden";
			document.getElementById("np").style.visibility="hidden";
			document.getElementById("ne").style.visibility="hidden";
			alert("Registrasi Gagal");
		return false;
	}
}

function form_reset()
{
		
			document.getElementById("sname").style.visibility="hidden";				
		
			document.getElementById("sphone").style.visibility="hidden";
		
			document.getElementById("saddress").style.visibility="hidden";
			document.getElementById("scity").style.visibility="hidden";
			document.getElementById("sprovince").style.visibility="hidden";
			document.getElementById("npass1").style.visibility="hidden";
			document.getElementById("npass2").style.visibility="hidden";
			document.getElementById("nemail").style.visibility="hidden";
			document.getElementById("sphoto").style.visibility="hidden";
			document.getElementById("tgl").style.visibility="hidden";
			document.getElementById("sname").style.visibility="hidden";				
			document.getElementById("ntgl").style.visibility="hidden";
			document.getElementById("ns").style.visibility="hidden";				
			document.getElementById("nt").style.visibility="hidden";
			document.getElementById("na").style.visibility="hidden";
			document.getElementById("nk").style.visibility="hidden";
			document.getElementById("np").style.visibility="hidden";
			document.getElementById("ne").style.visibility="hidden";
			document.getElementById("tgllahir").value = "";
			document.getElementById("nama").value="";
			document.getElementById("telephone").value="";
			document.getElementById("alamat").value="";
			document.getElementById("kota").value="";
			document.getElementById("provinsi").value="";
			document.getElementById("pass1").value="";
			document.getElementById("pass2").value="";
			document.getElementById("email").value="";
			document.getElementById("foto").value="";
		
		
			return true;
}

function register()
{
	document.getElementById("content").style.visibility="visible";
	return true;
}

function goto()
{
	if(document.getElementById("namelogin").value=="anto" && document.getElementById("passlogin").value=="anto")
	{
			location.href = "profil_anto.html";
	
	}
	else if(document.getElementById("namelogin").value=="ernest" && document.getElementById("passlogin").value=="ernest")
	{
			location.href = "profil_ernest.html";
	
	}
	else if(document.getElementById("namelogin").value=="puja" && document.getElementById("passlogin").value=="puja")
	{
			location.href = "profil_puja.html";
	
	}
}
*/