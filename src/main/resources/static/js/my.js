var passwordRedAlert = false;
var isForeigner = false;

function hideInfoPanel() {
    document.getElementById("alert-panel").style.display = "none";
}

function togglePass(imgPath, elementId, passId) {
    var x = document.getElementById(elementId);
    var e = document.getElementById("eye-" + elementId);
    if (x.type === "password") {
        x.type = "text";
        if (passwordRedAlert && elementId == passId) {
            e.src = imgPath + "/img/eye_close_red.svg";
        } else {
            e.src = imgPath + "/img/eye_close.svg";
        }
    } else {
        x.type = "password";
        if (passwordRedAlert && elementId == passId) {
            e.src = imgPath + "/img/eye_open_red.svg";
        } else {
            e.src = imgPath + "/img/eye_open.svg";
        }
    }
}

function cyrOnly(event) {
    var key = event.keyCode;
    if (key == 8 || key == 9 || key == 46 || key == 37 || key == 39)
        // backspace, tab, del, left, right
        return true;
    return /^[a-zA-ZабвгдежзийклмнопрстуфхцчшщьъюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЮЯ-]*$/.test(
        event.key
    );
}

function numOnly(event) {
    var key = event.keyCode;
    if (key == 8 || key == 9 || key == 46 || key == 37 || key == 39)
        // backspace, tab, del, left, right
        return true;
    return /^[0-9]*$/.test(event.key);
}

function numPlusOnly(event) {
    var key = event.keyCode;
    if (key == 8 || key == 9 || key == 46 || key == 37 || key == 39)
        // backspace, tab, del, left, right
        return true;
    return /^[0-9+]*$/.test(event.key);
}

function toggleLabel(elementId) {
    element = document.getElementById(elementId);
    elementLabel = document.getElementById("label-" + elementId);
    if (element.value) {
        elementLabel.style.visibility = "visible";
    } else {
        elementLabel.style.visibility = "hidden";
    }
}

function switchPage() {
    document
        .querySelectorAll(".email-page1")
        .forEach((a) => (a.style.display = "none"));
    document
        .querySelectorAll(".email-page2")
        .forEach((a) => (a.style.display = "block"));
}

function loginEnableSubmit() {
    var bt = document.getElementById("kc-login");
    var txt = document.getElementById("username");
    var pswd = document.getElementById("password");
    if (txt.value != "" && pswd.value) {
        bt.disabled = false;
    } else {
        bt.disabled = true;
    }
}

function forgotEnableSubmit() {
    var bt = document.getElementById("kc-login");
    var txt = document.getElementById("username");
    if (txt.value != "") {
        bt.disabled = false;
    } else {
        bt.disabled = true;
    }
}

function isValidEgn() {
    var egn = document.getElementById("user.attributes.nationalId").value;
    var surname = document.getElementById("midNameHolder");

    if (surname.style.display === "none") {
        surname.style.display = "block";
        isForeigner = false;
    }

    if (/^\d{10}$/.test(egn)) {
        checksum =
            egn.toString()[0] * 2 +
            egn.toString()[1] * 4 +
            egn.toString()[2] * 8 +
            egn.toString()[3] * 5 +
            egn.toString()[4] * 10 +
            egn.toString()[5] * 9 +
            egn.toString()[6] * 7 +
            egn.toString()[7] * 3 +
            egn.toString()[8] * 6;
        checksum = (checksum % 11) % 10;
        //console.log(checksum)
        if (egn.toString()[9] != checksum) {
            surname.style.display = "none";
            isForeigner = true;
        }
    }
}

function isValidId() {
    isValidEgn();
    var egn = document.getElementById("user.attributes.nationalId");
    if (/^\d{10}$/.test(egn.value)) {
        return true;
    } else {
        alertInvalidField("user.attributes.nationalId");
        return false;
    }
}

function fieldTypeValidate(elem) {
    var fieldSelector = document.getElementById(elem);
    var fieldValue = fieldSelector.value;
    var fieldLen = fieldValue.length;
    if (fieldSelector.classList.contains("warning-alert")) {
        fieldSelector.classList.remove("warning-alert");
    }
    var bottomMsgSelector = document.getElementById("alert-" + elem);
    // fieldSelector.classList.add('warning-alert');
    // if(hasEye) {
    //   if (fieldSelector.type == "password") {
    //     document.getElementById(eyeSelector).src = imgPathGen+"eye_open_red.svg";
    //   } else {
    //     document.getElementById(eyeSelector).src = imgPathGen+"eye_close_red.svg";
    //   }
    // }
    var eye = document.getElementById("eye-" + elem);
    if (eye) {
        if (fieldSelector.type === "password") {
            eye.src = imgPathGen + "eye_open.svg";
        } else {
            eye.src = imgPathGen + "eye_close.svg";
        }
    }
    bottomMsgSelector.classList.remove("alert-show");
    bottomMsgSelector.classList.add("alert-hide");
}

function passwordTypeValidate(elem) {
    isValidPassword = true;
    passwordSelector = document.getElementById(elem);
    var pass = passwordSelector.value;
    var passLen = pass.length;
    if (passwordSelector.classList.contains("warning-alert")) {
        passwordSelector.classList.remove("warning-alert");
    }
    var bottomMsgSelector = document.getElementById("alert-" + elem);
    if (bottomMsgSelector) {
        bottomMsgSelector.classList.remove("alert-show");
        bottomMsgSelector.classList.add("alert-hide");
    }

    // validate passowrd length
    if (passLen > 9) {
        makeGreen("valid10charsMin");
    } else {
        makeReset("valid10charsMin");
    }

    // check for capitals
    if (containsCapitalLetter(pass)) {
        makeGreen("valid2Capitals");
    } else {
        makeReset("valid2Capitals");
    }

    // check for number
    if (containsNumbers(pass)) {
        makeGreen("valid2Numbers");
    } else {
        makeReset("valid2Numbers");
    }

    // check for special characters
    if (containsSpecialChars(pass)) {
        makeGreen("validSpecials");
    } else {
        makeReset("validSpecials");
    }
}

function passwordResetValidate(elem) {
    passwordSelector = document.getElementById(elem);
    if (passwordSelector.value.length > 0) {
        passwordSelector.classList.remove("warning-alert");
        passwordTypeValidate();
    }
}

function passwordBlurValidate(elem) {
    isValidPassword = true;
    var pass = document.getElementById(elem).value;
    var passLen = pass.length;
    var greenArr = [];
    var makeGreenArr = [];
    var redArr = [];
    var makeRedArr = [];

    // validate passowrd length
    if (passLen > 9) {
        greenArr.push(msgValid10charsMin);
        makeGreenArr.push("valid10charsMin");
    } else {
        redArr.push(msgValid10charsMin);
        makeRedArr.push("valid10charsMin");
    }

    // check for capitals
    if (containsCapitalLetter(pass)) {
        greenArr.push(msgValid2Capitals);
        makeGreenArr.push("valid2Capitals");
    } else {
        redArr.push(msgValid2Capitals);
        makeRedArr.push("valid2Capitals");
    }

    // check for number
    if (containsNumbers(pass)) {
        greenArr.push(msgValid2Numbers);
        makeGreenArr.push("valid2Numbers");
    } else {
        redArr.push(msgValid2Numbers);
        makeRedArr.push("valid2Numbers");
    }

    // check for special characters
    if (containsSpecialChars(pass)) {
        greenArr.push(msgValidSpecials);
        makeGreenArr.push("validSpecials");
    } else {
        redArr.push(msgValidSpecials);
        makeRedArr.push("validSpecials");
    }

    // sort messages
    var passwordStatusContainer = document.getElementById("password-validation");
    passwordStatusContainer.innerHTML = msgValidRequirements;
    var redNum = redArr.length;
    if (redNum) {
        isValidPassword = false;
        for (let i = 0; i < redNum; i++) {
            passwordStatusContainer.innerHTML += redArr[i];
            makeRed(makeRedArr[i]);
        }
        document
            .getElementById(passwordValidateElem)
            .classList.add("warning-alert");
        passwordRedAlert = true;
        if (document.getElementById(passwordValidateElem).type === "password") {
            document.getElementById("eye-" + passwordValidateElem).src =
                imgPathGen + "eye_open_red.svg";
        } else {
            document.getElementById("eye-" + passwordValidateElem).src =
                imgPathGen + "eye_close_red.svg";
        }
    } else {
        passwordRedAlert = false;
    }

    var greenNum = greenArr.length;
    if (greenNum) {
        for (let i = 0; i < greenNum; i++) {
            passwordStatusContainer.innerHTML += greenArr[i];
            makeGreen(makeGreenArr[i]);
        }
    }
}

function containsNumbers(str) {
    return /\d/.test(str);
}

function containsCapitalLetter(str) {
    return /[A-Z, А-Я]/.test(str);
}

function containsSpecialChars(str) {
    const specialChars = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
    return specialChars.test(str);
}

function makeGreen(elem) {
    selector = document.getElementById(elem);
    selector.style.color = "green";
    selector.getElementsByClassName("validPrefix")[0].innerHTML = "✓ ";
    selector.getElementsByClassName("validPrefix")[0].style.fontSize = "16px;";
}

function makeRed(elem) {
    isValidPassword = false;
    selector = document.getElementById(elem);
    selector.style.color = "#DC3149";
    selector.getElementsByClassName("validPrefix")[0].innerHTML = "× ";
    selector.getElementsByClassName("validPrefix")[0].style.fontSize = "large";
    selector.getElementsByClassName("validPrefix")[0].style.lineHeight = "16px";
}

function makeReset(elem) {
    selector = document.getElementById(elem);
    selector.style.color = "unset";
    selector.getElementsByClassName("validPrefix")[0].innerHTML = "";
    passwordRedAlert = false;
    if (document.getElementById(passwordValidateElem).type == "password") {
        document.getElementById("eye-" + passwordValidateElem).src =
            imgPathGen + "eye_open.svg";
    } else {
        document.getElementById("eye-" + passwordValidateElem).src =
            imgPathGen + "eye_close.svg";
    }
}

// empty field validate on blur
function blurEmptyField(elem, hasEye) {
    var field = document.getElementById(elem);
    if (elem == "user.attributes.middleName" && isForeigner) return;

    if (!field.value) {
        alertField(elem, hasEye);
    }
}

// empty field validate
function validateEmptyField(elem, hasEye) {
    var field = document.getElementById(elem);
    if (!field.value) {
        field.focus();
        //alertField(elem, hasEye);
        return false;
    }

    return true;
}

function isValidEmail() {
    var emailField = document.getElementById("email");
    if (emailField.value == "") return true;
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,10})+$/.test(emailField.value)) {
        return true;
    }

    alertInvalidField("email");
    return false;
}

function validateForm() {
    if (!validateEmptyField("user.attributes.nationalId", false)) {
        return false;
    }

    if (!isValidId()) {
        return false;
    }
    /*
    if(!passwordRedAlert) {
      document.getElementById("password").focus();
    }
  */
    if (!validateEmptyField("firstName", false)) {
        return false;
    }

    if (
        !isForeigner &&
        !validateEmptyField("user.attributes.middleName", false)
    ) {
        return false;
    }

    if (!validateEmptyField("lastName", false)) {
        return false;
    }

    if (!validateEmptyField("email", false)) {
        return false;
    }

    if (!isValidEmail()) {
        return false;
    }

    // if (!validateEmptyField("password", true)) {
    //   return false;
    // }

    // if (!validateEmptyField("password-confirm", true)) {
    //   return false;
    // }
    mobileNumberClearWhiteSpace();
    return true;
}

function validateUpdateFormOnSubmit() {
    passwordNew = document.getElementById(passwordValidateElem);
    if (!passwordNew.value) {
        passwordNew.focus();
        return false;
    }

    if (passwordRedAlert) {
        document.getElementById(passwordValidateElem).focus();
    }
    if (passwordRedAlert) return false;

    passwordConfirm = document.getElementById("password-confirm");
    if (!passwordConfirm.value) {
        passwordConfirm.focus();
        alertField("password-confirm", true);
        return false;
    }

    return true;
}

function alertField(fieldId, hasEye) {
    var fieldSelector = document.getElementById(fieldId);
    var eyeSelector = "eye-" + fieldId;
    var bottomMsgSelector = document.getElementById("alert-" + fieldId);
    fieldSelector.classList.add("warning-alert");
    fieldSelector.placeholder.color = "red";
    if (hasEye) {
        if (fieldSelector.type == "password") {
            document.getElementById(eyeSelector).src =
                imgPathGen + "eye_open_red.svg";
        } else {
            document.getElementById(eyeSelector).src =
                imgPathGen + "eye_close_red.svg";
        }
    }
    bottomMsgSelector.innerHTML = requiredField;
    bottomMsgSelector.classList.add("alert-show");
    bottomMsgSelector.classList.remove("alert-hide");
}

function alertInvalidField(fieldId) {
    var fieldSelector = document.getElementById(fieldId);
    var bottomMsgSelector = document.getElementById("alert-" + fieldId);
    fieldSelector.classList.add("warning-alert");
    bottomMsgSelector.innerHTML = invalidData;
    bottomMsgSelector.classList.add("alert-show");
    bottomMsgSelector.classList.remove("alert-hide");
}

function normalizeField(fieldId) {
    var fieldSelector = document.getElementById(fieldId);
    var bottomMsgSelector = document.getElementById("alert-" + fieldId);
    if (fieldSelector.value.length == 1) {
        fieldSelector.classList.remove("warning-alert");
        var eyeSelector = "eye-" + fieldId;
        if (fieldSelector.type == "password") {
            document.getElementById(eyeSelector).src = imgPathGen + "eye_open.svg";
        } else {
            document.getElementById(eyeSelector).src = imgPathGen + "eye_close.svg";
        }
    }
    bottomMsgSelector.classList.remove("alert-show");
    bottomMsgSelector.classList.add("alert-hide");
}

// function phoneMask(e) {
//   if(e.keyCode < 48 || e.keyCode > 57) {
//     e.preventDefault();
//     return true;
//   }
//   var phoneCodeElem = document.getElementById('mobileNumberCode');
//   var phoneNumberElem = document.getElementById('mobileNumber');
//   var numLen = phoneNumberElem.value.length;
//   if(phoneNumberElem.value.startsWith('0')) {
//     phoneNumberElem.value = phoneNumberElem.value.substring(1);
//   }
//   if(phoneCodeElem.value == '+359') {
//     if(numLen == 2 || numLen == 6) {
//       phoneNumberElem.value = phoneNumberElem.value + ' ';
//     }
//     if(numLen > 10) {
//       phoneNumberElem.value = phoneNumberElem.value.slice(0, 10);
//     }
//   }
// }

function phoneMaskRegistration(e) {
    var phoneCodeElem = document.getElementById("mobileNumberCode");
    var phoneNumberElem = document.getElementById("mobileNumber");
    var numLen = phoneNumberElem.value.length;
    var testMobileNumberForInvalidChars = /^(?=.*\d)[\d ]+$/.test(
        phoneNumberElem.value
    );
    if (!testMobileNumberForInvalidChars) {
        phoneNumberElem.value = "";
        e.preventDefault();
        return;
    }
    if (e.keyCode < 48 || e.keyCode > 57) {
        e.preventDefault();
        return true;
    }
    const key = e.inputType;
    if (key == "deleteContentBackward") {
        return true;
    }

    phoneNumberElem.value.trim();

    if (phoneNumberElem.value.startsWith("0")) {
        phoneNumberElem.value = phoneNumberElem.value.substring(1);
    }
    if (phoneCodeElem.value == "+359") {
        if (numLen == 2 || numLen == 6) {
            phoneNumberElem.value = phoneNumberElem.value + " ";
        }
        if (numLen > 10) {
            phoneNumberElem.value = phoneNumberElem.value.slice(0, 11);
        }
    }
}

function changeMaskRegistration() {
    var phoneCodeElem = document.getElementById("mobileNumberCode");
    var phoneNumberElem = document.getElementById("mobileNumber");
    var phone = phoneNumberElem.value.trim();
    phone = phone.replace(/[^0-9]/g, "");
    if (phoneCodeElem.value == "+359") {
        phone = [phone.slice(0, 2), " ", phone.slice(2)].join("");
        phone = [phone.slice(0, 6), " ", phone.slice(6)].join("");
        phone = phone.substring(0, 11);
        phoneNumberElem.value = phone;
    } else {
        phoneNumberElem.value = phone.replace(/\s/g, "");
    }
}

function validatePhoneRegistration() {
    var phoneCodeElem = document.getElementById("mobileNumberCode");
    var phoneNumberElem = document.getElementById("mobileNumber");
    var isValidPhone = false;
    if (phoneCodeElem.value == "+359") {
        if (phoneNumberElem.value.length == 11) {
            isValidPhone = true;
        }
    } else {
        if (phoneNumberElem.value.length > 8) {
            isValidPhone = true;
        }
    }

    if (!isValidPhone) {
        alertInvalidField("mobileNumber");
        return false;
    }

    return isValidPhone;
}

function alertPhoneField() {
    var phoneContainerElem = document.getElementById("mobile-container");
    var phoneNumberElem = document.getElementById("mobileNumber");
    var bottomMsgSelector = document.getElementById("alert-mobileNumber");
    phoneContainerElem.classList.add("phone-alert");
    phoneNumberElem.classList.add("warning-alert");
    bottomMsgSelector.classList.add("alert-phone");
    bottomMsgSelector.classList.remove("alert-hide");
}

function resetPhoneField() {
    var phoneContainerElem = document.getElementById("mobile-container");
    var phoneNumberElem = document.getElementById("mobileNumber");
    var bottomMsgSelector = document.getElementById("alert-mobileNumber");

    if (phoneNumberElem.classList.contains("warning-alert")) {
        phoneNumberElem.classList.remove("warning-alert");
        phoneContainerElem.classList.remove("phone-alert");
        bottomMsgSelector.classList.remove("alert-phone");
        bottomMsgSelector.classList.add("alert-hide");
    }
}

window.onload = function () {
    isValidEgn;

    //   const form = document.getElementById('kc-register-form');
    //   form.addEventListener("change", () => {
    //     document.getElementById('regBtn').disabled = !validateForm();
    //   });
};

//Taking the username from url - sent by other system, and injecting it automatically in input name
document.addEventListener("DOMContentLoaded", function () {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const found = urlParams.get("logusername");
    if (found != null) {
        document.getElementById("username").value = found;
    }
});

function phoneMask(e) {
    var evrotrustDropdown = document.getElementById("dropdown");
    if (evrotrustDropdown.value === "mobile_number") {
        var phoneCodeElem = document.getElementById("mobileNumberCode");
        var phoneNumberElem = document.getElementById("input_evrotrust");
        var numLen = phoneNumberElem.value.length;
        var testMobileNumberForInvalidChars = /^(?=.*\d)[\d ]+$/.test(
            phoneNumberElem.value
        );
        if (!testMobileNumberForInvalidChars) {
            phoneNumberElem.value = "";
            e.preventDefault();
            return;
        }
        if (e.keyCode < 48 || e.keyCode > 57) {
            e.preventDefault();
            return true;
        }
        const key = e.inputType;
        if (key == "deleteContentBackward") {
            return true;
        }

        phoneNumberElem.value.trim();

        if (phoneNumberElem.value.startsWith("0")) {
            phoneNumberElem.value = phoneNumberElem.value.substring(1);
        }
        if (phoneCodeElem.value == "+359") {
            if (numLen == 2 || numLen == 6) {
                phoneNumberElem.value = phoneNumberElem.value + " ";
            }
            if (numLen > 10) {
                phoneNumberElem.value = phoneNumberElem.value.slice(0, 11);
            }
        }
    }
}

function validatePhone() {
    var phoneCodeElem = document.getElementById("mobileNumberCode");
    var phoneNumberElem = document.getElementById("input_evrotrust");
    var isValidPhone = false;
    if (phoneCodeElem.value == "+359") {
        if (phoneNumberElem.value.length == 11) {
            isValidPhone = true;
        }
    } else {
        if (phoneNumberElem.value.length > 8) {
            isValidPhone = true;
        }
    }

    if (!isValidPhone) {
        alertInvalidField("mobileNumber");
        return false;
    }

    return isValidPhone;
}
function changeMask() {
    var phoneCodeElem = document.getElementById("mobileNumberCode");
    var phoneNumberElem = document.getElementById("input_evrotrust");
    var phone = phoneNumberElem.value.trim();
    phone = phone.replace(/[^0-9]/g, "");
    if (phoneCodeElem.value == "+359") {
        phone = [phone.slice(0, 2), " ", phone.slice(2)].join("").trim();
        phone = [phone.slice(0, 6), " ", phone.slice(6)].join("").trim();
        phone = phone.substring(0, 11);

        phoneNumberElem.value = phone;
    } else {
        phoneNumberElem.value = phone.replace(/\s/g, "");
    }
}

function changeUserInput() {
    var evrotrustDropdown = document.getElementById("dropdown");
    var mobileNumberCode = document.getElementById("mobileNumberCode");
    var mobileWrapper = document.getElementById("mobileNumberWprapper");
    var evrotrustInput = document.getElementById("input_evrotrust");
    var mobilePhoneHint = document.getElementById("mobilePhoneHint");
    evrotrustInput.value = "";
    if (evrotrustDropdown.value !== "") {
        evrotrustInput.removeAttribute("disabled");
        evrotrustInput.classList.remove("disabled");
    }
    if (evrotrustDropdown.value == "mobile_number") {
        mobileNumberCode.style.display = "block";
        mobilePhoneHint.style.display = "block";
        mobileWrapper.style.display = "flex";
    } else if (evrotrustDropdown.value == "nationalId") {
        mobileNumberCode.style.display = "none";
        mobilePhoneHint.style.display = "none";
        mobileWrapper.style.display = "none";
    } else {
        mobilePhoneHint.style.display = "none";
        mobileNumberCode.style.display = "none";
        mobileWrapper.style.display = "none";
    }
}

function openInfoModalEvrotrust() {
    var infoModalEvrotrust = document.getElementById("infoModalEvrotrust");
    infoModalEvrotrust.style.display = "block";
}
function closeInfoModalEvrotrust() {
    var infoModalEvrotrust = document.getElementById("infoModalEvrotrust");
    infoModalEvrotrust.style.display = "none";
}
window.onclick = function (event) {
    var infoModalEvrotrust = document.getElementById("infoModalEvrotrust");
    if (event.target == infoModalEvrotrust) {
        infoModalEvrotrust.style.display = "none";
    }
};

function disableIfDropdownIfNotSelected() {
    var evrotrustDropdown = document.getElementById("dropdown");
    var evrotrustInput = document.getElementById("input_evrotrust");
    if (evrotrustDropdown.value === "") {
        evrotrustInput.disabled = true;
        evrotrustInput.classList.add("disabled");
    } else {
        evrotrustInput.disabled = false;
        evrotrustInput.classList.remove("disabled");
    }
}

function disableEvrotrustInput() {
    var evrotrustDropdown = document.getElementById("dropdown");
    var mobileNumberCode = document.getElementById("mobileNumberCode");
    var mobileWrapper = document.getElementById("mobileNumberWprapper");
    var evrotrustInput = document.getElementById("input_evrotrust");
    var evrotrustWrapper = document.getElementById("evrotrustWrapper");
    var userName = document.getElementById("username");
    var password = document.getElementById("password");
    if (userName.value !== "" || password.value !== "") {
        evrotrustDropdown.disabled = true;
        mobileWrapper.disabled = true;
        evrotrustInput.disabled = true;
        mobileNumberCode.disabled = true;
        mobileNumberCode.classList.add("disabled");
        evrotrustInput.classList.add("disabled");
        mobileWrapper.classList.add("disabled");
        evrotrustDropdown.classList.add("disabled");
        evrotrustWrapper.classList.add("disabled");
        evrotrustInput.value = "";
    } else {
        mobileNumberCode.classList.remove("disabled");
        evrotrustWrapper.classList.remove("disabled");
        evrotrustInput.classList.remove("disabled");
        mobileWrapper.classList.remove("disabled");
        evrotrustDropdown.classList.remove("disabled");
        evrotrustDropdown.disabled = false;
        mobileWrapper.disabled = false;
        evrotrustInput.disabled = false;
        mobileNumberCode.disabled = false;
        disableIfDropdownIfNotSelected();
    }
}
function disableLoginInput() {
    var evrotrustInput = document.getElementById("input_evrotrust");
    var userName = document.getElementById("username");
    var password = document.getElementById("password");
    // var usernameContainer = document.getElementById("usernameContainer");
    // var passwordContainer = document.getElementById("passwordContainer");

    if (evrotrustInput.value !== "") {
        userName.disabled = true;
        password.disabled = true;
        userName.style.cursor = "not-allowed";
        password.style.cursor = "not-allowed";
        // usernameContainer.classList.add("disabled");
        // passwordContainer.classList.add("disabled");
        userName.value = "";
        password.value = "";
    } else {
        userName.disabled = false;
        password.disabled = false;
        userName.style.cursor = "auto";
        password.style.cursor = "auto";
        // usernameContainer.classList.remove("disabled");
        // passwordContainer.classList.remove("disabled");
    }
}

function showSpinner() {
    document.getElementById("spinner").style.display = "none";
    document.getElementById("spinner").style.display = "flex";
}

function setSpinnerTextEvrotrust() {
    const evrotrustWaitTitle1 = document.getElementById("evrotrustWaitTitle1");
    const evrotrustWaitTitle2 = document.getElementById("evrotrustWaitTitle2");
    const evrotrustInput = document.getElementById("input_evrotrust");
    if (evrotrustInput.value !== "") {
        evrotrustWaitTitle1.classList.remove("hidden");
        evrotrustWaitTitle1.classList.add("active");

        setInterval(() => {
            if (evrotrustWaitTitle1.classList.contains("active")) {
                evrotrustWaitTitle2.classList.remove("hidden");
                evrotrustWaitTitle2.classList.add("active");
                evrotrustWaitTitle1.classList.remove("active");
                evrotrustWaitTitle1.classList.add("hidden");
            } else {
                evrotrustWaitTitle2.classList.add("hidden");
                evrotrustWaitTitle2.classList.remove("active");
                evrotrustWaitTitle1.classList.add("active");
                evrotrustWaitTitle1.classList.remove("hidden");
            }
        }, 3000);
    }
}

function mobileNumberClearWhiteSpace() {
    const mobileNumberValLogin = document.getElementById("input_evrotrust");
    const mobileNumberValRegister = document.getElementById("mobileNumber");
    if (mobileNumberValLogin) {
        const trimVal = mobileNumberValLogin.value.replace(/\s/g, "");
        mobileNumberValLogin.defaultValue = trimVal;
        mobileNumberValLogin.value = trimVal;
    }
    if (mobileNumberValRegister) {
        const trimVal = mobileNumberValRegister.value.replace(/\s/g, "");
        mobileNumberValRegister.defaultValue = trimVal;
        mobileNumberValRegister.value = trimVal;
    }
}
