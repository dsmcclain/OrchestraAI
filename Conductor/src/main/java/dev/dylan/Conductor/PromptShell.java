package dev.dylan.Conductor;

import dev.dylan.Conductor.service.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Option;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PromptShell {

  private final PromptService promptService;

  @Autowired
  public PromptShell(PromptService promptService) {
    this.promptService = promptService;
  }
  @ShellMethod(key= "hello", value = "Say Hello")
  public String helloWorld() {
    return "Hello World";
  }

  @ShellMethod(key = "new", value = "new prompt")
  public String newPrompt(@Option(required = true) String input) {
    String uuid = promptService.generateNewPrompt(input);
    return "A new prompt was created: " + uuid;
  }

  @ShellMethod(key = "get", value = "prompt uuid")
  public String getResult(@Option(required = true) String uuid) {
    return promptService.getPrompt(uuid);
  }
}
