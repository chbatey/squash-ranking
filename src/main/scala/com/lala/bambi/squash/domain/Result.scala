package com.lala.bambi.squash.domain

/**
 * Created with IntelliJ IDEA.
 * User: chbatey
 * Date: 06/10/2013
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
case class Result(player1: Player, player2: Player)

case class Player(name: String, score: Int)
