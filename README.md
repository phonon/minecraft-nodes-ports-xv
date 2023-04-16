# nodes-ports-xv

version: 1.16.5 or 1.18.2 i think

nodes ports warp addon with integration with vehicles (xc/xv)

dependencies:
- paperweight nms: <https://github.com/PaperMC/paperweight>
- nodes: <https://github.com/phonon/minecraft-nodes>
- xc/xv: <https://github.com/phonon/minecraft-xc>

this uses local files as dependencies so structure folders as:
```
work/
 ├─ nodes/               - Nodes main repo
 |   └─ nodes/           - Nodes plugin directory
 |       ├─ build/
 |       |   └─ libs/    - Nodes dependency build folder
 |       └─ src/
 ├─ xc/                  - xc/xv combat/vehicles main repo
 |   └─ xv/              - vehicles
 |       ├─ build/
 |       |   └─ libs/    - vehicle dependency build folder
 |       └─ src/
 └─ nodes-ports-xv/      - This plugin/repo
     └─ src/
```