package com.revelcw.avaloncore.utils

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

object ChatFormat {

  private var replaceMap: Map<String, String> = object : LinkedHashMap<String, String>() {
    init {
      put("&black", "§0")
      put("&dark_blue", "§1")
      put("&dark_green", "§2")
      put("&dark_aqua", "§3")
      put("&dark_red", "§4")
      put("&dark_purple", "§5")
      put("&gold", "§6")
      put("&gray", "§7")
      put("&dark_gray", "§8")
      put("&blue", "§9")
      put("&green", "§a")
      put("&aqua", "§b")
      put("&red", "§c")
      put("&light_purple", "§d")
      put("&yellow", "§e")
      put("&white", "§f")
      put("&cypher", "§k")
      put("&obfuscated", "§k")
      put("&bold", "§l")
      put("&strikethrough", "§m")
      put("&strike", "§m")
      put("&underline", "§n")
      put("&italic", "§o")
      put("&reset", "§r")
      put("&r", "§r")
    }
  }

  val PREFIX = convert("&yellow[&redA&yellow:&light_purpleC&yellow]&r ")

  fun convert(unformattedString: String): String {
    var formattedString = unformattedString
    for ((key, value) in replaceMap) {
      formattedString = formattedString.replace(key, value)
    }
    return formattedString
  }

  fun sendInfo(sender: CommandSender, message: String) {
    sender.sendMessage(PREFIX + convert(message))
  }
  fun sendSuccess(sender: CommandSender, message: String) {
    sender.sendMessage(PREFIX + ChatColor.DARK_GREEN + convert(message))
  }
  fun sendWarning(sender: CommandSender, message: String) {
    sender.sendMessage(PREFIX + ChatColor.YELLOW + convert(message))
  }
  fun sendError(sender: CommandSender, message: String) {
    sender.sendMessage(PREFIX + ChatColor.RED + convert(message))
  }
}