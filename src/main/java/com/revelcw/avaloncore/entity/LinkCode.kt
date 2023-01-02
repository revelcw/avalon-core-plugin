package com.revelcw.avaloncore.entity

import java.util.*
import kotlin.math.pow

/**
 * Create a link code entity.
 * LinkCode takes in a 6 char long string of 0-9,A-Z. Case insensitive. Dashes are allowed, and don't count against the 6.
 *
 * @param code The code
 */
data class LinkCode(private val code: String) {
  /**
   * The LinkCode without dashes or spaces. Use this for storing code.
   */
  val strippedCode = stripCode(code)

  /**
   * LinkCode with a dash in the middle. Use this for displaying to user.
   */
  val dashedCode = strippedCode.slice(0..2) + "-" + strippedCode.slice(3..5)

  init {
    require(strippedCode.length == 6) { "Code must be 6 digits (excluding dashes)" }
    require(strippedCode.toLong(36) < 36.0.pow(6.0)) { "Code must be 0-9,A-Z" }

  }


  companion object {
    private fun stripCode(code: String): String {
      return code.replace("-", "", true).replace(" ", "", true).uppercase(Locale.getDefault())
    }

    /**
     * Tells you if the passed in string is a LinkCode
     * @param code The string to check
     * @return Whether the string is valid
     */
    fun isValid(code: String): Boolean {
      val strippedCode = stripCode(code)
      println(strippedCode)

      println(strippedCode.toLong(36)< 32.0.pow(6.0))
      return strippedCode.length == 6 && strippedCode.toLong(36) < 36.0.pow(6.0)
    }
  }
}