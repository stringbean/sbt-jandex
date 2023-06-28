package com.example

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class Greeter {
  def greet(name: String): String = {
    s"Hello, $name!"
  }
}