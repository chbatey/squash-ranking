package com.lala.bambi.squash

import com.escalatesoft.subcut.inject.NewBindingModule
import com.lala.bambi.squash.dao.{ResultRepoMongoBacked, ResultRepo}

/**
 * Created with IntelliJ IDEA.
 * User: chbatey
 * Date: 06/10/2013
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
object ProjectConfiguration extends NewBindingModule(module => {
  import module._

  bind [ResultRepo] toSingle new ResultRepoMongoBacked
})
