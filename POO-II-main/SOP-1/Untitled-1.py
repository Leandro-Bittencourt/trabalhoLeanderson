class Tarefa:
    def __init__(self, vm_id, vcpus, exec_time, arrival_time, priority, is_allocated):
        self.vm_id = vm_id
        self.vcpus = vcpus
        self.exec_time = exec_time
        self.arrival_time = arrival_time
        self.priority = priority
        self.remaining_time = exec_time
        self.is_allocated = is_allocated

# Criação das tarefas pré-definidas
tarefas = [
    Tarefa("VM1", 4, 35, 40, 3, False),
    Tarefa("VM2", 2, 40, 45, 1, False),
    Tarefa("VM3", 4, 30, 45, 1, False),
    Tarefa("VM4", 4, 25, 20, 3, False),
    Tarefa("VM5", 2, 40, 25, 3, False),
    Tarefa("VM6", 4, 20, 20, 2, False),
    Tarefa("VM7", 1, 20, 40, 0, False),
    Tarefa("VM8", 4, 20, 30, 1, False),
    Tarefa("VM9", 4, 10, 20, 2, False),
    Tarefa("VM10", 1, 35, 30, 3, False),
]

vetor = [Tarefa] * 4
time = 0
step = 5

def is_task_valid(tarefa, time):
    if time < tarefa.arrival_time:
        return False
    if tarefa.remaining_time <= 0:
        return False
    if tarefa.is_allocated:
        return False
    return True

while time < 200:
    for tarefa in tarefas:
        if is_task_valid(tarefa, time):
            CPUs = tarefa.vcpus
            for i in range(CPUs):
                if vetor[i] == Tarefa or vetor[i].remaining_time <= 0:
                    vetor[i] = tarefa
                    tarefa.is_allocated = True
                    break
                
            tarefa.remaining_time -= step
        
    time += step

"""     print(time, ": ")
    for tarefa in vetor:
        if tarefa:
            tarefa.vm_id """
