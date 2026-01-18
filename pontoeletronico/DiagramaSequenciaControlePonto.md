@startuml
title Sistema de Controle de Ponto - Endpoint Único

actor Funcionario
participant "API Gateway / Controller" as Controller
participant "Keycloak" as Keycloak
participant "Ponto Service\n(Camada de Domínio)" as Service
participant "Ponto Repository\n(Porta de Saída)" as Repository
database "NoSQL DB" as DB
participant "Prometheus" as Metrics

Funcionario -> Controller: POST /ponto (token JWT)
activate Controller

Controller -> Keycloak: Validar token
activate Keycloak
Keycloak --> Controller: Token válido
deactivate Keycloak

Controller -> Service: registrarPonto(funcionarioId, dataHora)
activate Service

Service -> Repository: buscarRegistrosDoDia(funcionarioId)
activate Repository
Repository -> DB: query registros do dia
DB --> Repository: lista de registros
Repository --> Service: registros encontrados
deactivate Repository

alt Registro duplicado ou horário fora de ordem
    Service --> Controller: Erro de validação
    Controller --> Funcionario: 400 Bad Request
else Limite de 10 horas atingido
    Service -> Service: fecharPontoAutomaticamente()
    Service --> Controller: Ponto fechado automaticamente
    Controller --> Funcionario: 200 OK
else Registro válido
    Service -> Service: aplicarRegrasDeNegocio()
    note right of Service
      - Máx. 10h por dia
      - Mín. 2 entradas e 2 saídas
      - Almoço obrigatório
    end note

    Service -> Repository: salvarRegistro()
    activate Repository
    Repository -> DB: insert/update
    DB --> Repository: OK
    Repository --> Service: Registro salvo
    deactivate Repository

    Service --> Controller: Registro efetuado
    Controller --> Funcionario: 201 Created
end

Service -> Metrics: registrar métricas
deactivate Service
deactivate Controller
@enduml
