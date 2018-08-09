package com.babynames.core.presentation.account

import android.accounts.Account

object AccountsManager {

    const val REQUEST_PERMISSIONS_CODE: Int = 1024
    const val REQUEST_CODE_FOR_REMOVE_ACCOUNT: Int = 1001
    const val RESULT_CODE_ACCOUNT_CREATED: Int = 1034

    /**
     * Metodo que itera sobre las diferentes cuentas para verificar si existe
     * una cuenta asociada a la aplicacion
     *
     * @param packageName
     * @param accounts
     * @return
     */
    fun accountExistByType(packageName: String, accounts: Array<out Account>): Boolean {
        return accounts.any { it.type == packageName }
    }

    /**
     * Metodo que itera sobre las cuentas para verificar que exista una cuenta que tenga
     * asociada un determinado accountName (email)
     *
     * @param accountName
     * @param accounts
     * @return
     */
    fun accountExistByName(accountName: String, accounts: Array<Account>): Boolean {
        return accounts.any { it.name == accountName }
    }

    /**
     * Metodo que itera sobre las cuentas y retorna la que esta asociada con la aplicacion
     * usando el tipo(nombre de paquete)
     *
     * @param accountType
     * @param accounts
     * @return
     */
    fun getAccountByType(accountType: String, accounts: Array<out Account>): Account? {
        return accounts.firstOrNull { it.type == accountType }
    }

    /**
     * Metodo que itera sobre las cuentas y retorna la que esta asociada a un determinado
     * accountname (email)
     *
     * @param accountName
     * @param accounts
     * @return
     */
    fun getAccountByName(accountName: String, accounts: Array<out Account>): Account? {
        return accounts.firstOrNull { it.name == accountName }
    }
}