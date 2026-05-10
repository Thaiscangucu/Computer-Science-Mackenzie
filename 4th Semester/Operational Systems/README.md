# Memory Paging Simulator

A memory paging simulation written in C that demonstrates how operating systems manage virtual-to-physical address translation using page tables and page replacement algorithms.

---

## Overview

This simulator models a simplified OS memory management unit (MMU) with the following characteristics:

- **Page size:** 4 KB
- **Physical memory:** 16 KB (4 frames)
- **Process size:** 16 KB per process (4 pages each)
- **Active processes:** 3
- **Total accesses per simulation:** 20

The program runs two back-to-back simulations — one using **FIFO** and one using **Random** page replacement — and prints a full trace of each memory access, page fault events, physical memory state, and final statistics.

---

## Features

- Virtual-to-physical address translation
- Page fault detection and handling
- Two page replacement algorithms:
  - **FIFO (First-In, First-Out)** — evicts the page that has been in memory the longest
  - **Random** — evicts a randomly chosen page
- Per-simulation statistics: total accesses, total page faults, and page fault rate
- Physical memory state display after each access

---

## Project Structure

```
.
└── main.c      # Single-file implementation containing all structs, logic, and entry point
```

---

## Requirements

- A C compiler supporting **C99** or later (e.g., `gcc`, `clang`)
- Standard C library (`stdio.h`, `stdlib.h`, `time.h`)

No external libraries or dependencies are required.

---

## Compilation

### Using GCC

```bash
gcc -std=c99 -Wall -o paging_simulator main.c
```

### Using Clang

```bash
clang -std=c99 -Wall -o paging_simulator main.c
```

### Using Make (optional)

If you prefer, create a simple `Makefile`:

```makefile
CC = gcc
CFLAGS = -std=c99 -Wall
TARGET = paging_simulator

all: $(TARGET)

$(TARGET): main.c
	$(CC) $(CFLAGS) -o $(TARGET) main.c

clean:
	rm -f $(TARGET)
```

Then build with:

```bash
make
```

---

## Running

```bash
./paging_simulator
```

The program requires no arguments. It will automatically run two simulations (FIFO then Random) and print all output to stdout.

---

## Sample Output

```
--- NOVA SIMULAÇÃO ---
Criando Processos
Processo 1 criado: 4 páginas (16384 bytes)
...
Tempo t = 1 : Processo 1 acessa o endereço virtual 1042
PAGE FAULT: Página 0 do processo 1 não está em memória física
Carregando página em memória...
...
======== ESTATÍSTICAS DA SIMULAÇÃO ========
Total de acessos à memória: 28
Total de page faults: 12/20
Taxa de page faults: 60.00%
```

> Because Random replacement is non-deterministic, results will vary between runs.

---

## How It Works

1. **Initialization** — the simulator creates 3 processes, each with 4 virtual pages. Physical memory starts empty (0/4 frames used).
2. **Access loop** — the simulator performs 20 memory accesses: the first 12 load all pages sequentially (3 processes × 4 pages), and the remaining 8 access random pages.
3. **Page fault handling** — on a miss, the simulator checks whether a free frame exists. If so, the page is loaded directly. If memory is full, a victim page is chosen using the active replacement algorithm.
4. **Address translation** — virtual addresses are structured as `(page_number * 1000) + offset`. Physical addresses are computed as `(frame * 1000) + offset`.
5. **Statistics** — at the end of each simulation, total accesses, page faults, and the fault rate are reported.

---

**Authors:**
- Fabio Oliveira da Silva — RA: 10420458
- Patrick Rocha de Andrade — RA: 10410902
- Thais Ferreira Canguçu — RA: 10403283


