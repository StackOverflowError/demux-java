package com.antpool.demux.example.eos.transfers;

import com.antpool.demux.handler.Effect;
import com.antpool.demux.model.BlockInfo;
import com.antpool.demux.reader.eos.model.EosPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransferEffects<TState extends TransferState, TPayload extends EosPayload, TContext>  extends Effect<TState, TPayload, TContext> {

    private ObjectWriter mapper;

    public TransferEffects(String actionType) {
        super(actionType);
        mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
    }

    @Override
    public void execute(TState state, TPayload payload, BlockInfo blockInfo, TContext context) {
        try {
            log.info("State updated:\n {}", mapper.writeValueAsString(state));
        } catch (Exception ex) {
            log.error("effect error", ex);
        }
    }
}
