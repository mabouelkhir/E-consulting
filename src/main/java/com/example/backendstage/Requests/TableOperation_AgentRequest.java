package com.example.backendstage.Requests;

import com.example.backendstage.Models.Candidat;
import com.example.backendstage.Models.Operation_Agent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableOperation_AgentRequest {

    private String montant;
    private Operation_Agent operation_agent;

}
