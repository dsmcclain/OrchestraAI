package dev.dylan.StringSection.services;

public class BuilderWithProducer {

    public BuilderWithProducer() {

    }
//    private KafkaProducer kafkaProducer;
//    @Autowired
//    public void PromptBuilderWIthProducer(KafkaProducer kafkaProducer) {
//        this.kafkaProducer = kafkaProducer;
//    }
//    @Override
//    public Prompt build() {
//        Prompt prompt = super.build();
//        try {
//            kafkaProducer.sendMessage("prompts", prompt);
//        } catch (JsonProcessingException e) {
//            System.out.println("Unable to publish prompt: " + prompt.toString());
//            e.printStackTrace();
//        }
//        return prompt;
//    }
}