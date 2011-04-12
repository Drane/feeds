function checkForm(form, ccId) {
  var name = form[ccId + ':name'].value;
  var pwd = form[ccId + ':password'].value;

  if (name == "" || pwd == "") {
    alert("Please enter name and password.");
    return false;
  }
  return true;
}