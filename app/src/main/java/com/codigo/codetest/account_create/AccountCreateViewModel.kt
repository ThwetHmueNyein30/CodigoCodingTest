package com.codigo.codetest.account_create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AccountCreateViewModel @Inject constructor(): ViewModel() {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val emailAddress = MutableLiveData<String>()
    val dateOfBirth = MutableLiveData<String>()
    val dateOfBirthForServer = MutableLiveData<String?>()
    val gender = MutableLiveData<String>()
    val nationality = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val code = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val validForm = MutableLiveData(false)

    fun formValidate(){
        validForm.value = !firstName.value.isNullOrBlank()
                && !lastName.value.isNullOrBlank()
                && !dateOfBirth.value.isNullOrBlank()
                && !gender.value.isNullOrBlank()
                && !nationality.value.isNullOrBlank()
                && !country.value.isNullOrBlank()
                && !code.value.isNullOrBlank()
                && validPhone()
                &&validEmail()
    }

    private fun validPhone(): Boolean{
        return if(phoneNumber.value.isNullOrBlank()){
            false
        }else{
            phoneNumber.value?.length!! > 9
        }
    }

    private fun validEmail(): Boolean{
        return if(emailAddress.value.isNullOrBlank()){
            false
        }else{
            "[a-zA-Z\\d._-]+@[a-zA-Z\\d-]+\\.[a-zA-Z.]{2,18}".toRegex()
                .matches(emailAddress.value!!)
        }
    }

    fun chooseGender(s: String) {
        gender.value = s
        formValidate()
    }

    fun dobChange(date: Date) {
        dateOfBirthForServer.value = sdfForServer.format(date)
        dateOfBirth.value = sdf.format(date)
        formValidate()
    }
}