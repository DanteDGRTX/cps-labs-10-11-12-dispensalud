#!/usr/bin/env bash
set -euo pipefail

# Ejecutar una rama a la vez; el integrante responsable revisa, prueba y hace el commit.
branches=(
  feature/lab11-caso1-vet-ronal
  feature/lab11-caso2-owner-leonel
  feature/lab11-caso3-specialty-luis
  feature/lab11-caso4-visit-paola
  feature/lab11-caso5-type-ronal-luis
  feature/lab11-caso6-vetspecialty-leonel-paola
)

if [[ "${1:-}" == "--create" ]]; then
  for branch in "${branches[@]}"; do
    git switch main
    git switch -c "$branch"
    echo "Rama creada: $branch. Implementar el caso, ejecutar ./mvnw clean test y confirmar con el autor real."
  done
  git switch main
  exit 0
fi

printf 'Ramas propuestas para el Lab 11:\n'
printf '  %s\n' "${branches[@]}"
printf '\nCrear las ramas con: bash git_workflow_lab11.sh --create\n'
printf 'Después de trabajar en cada rama, ejecutar ./mvnw clean test y crear un Pull Request revisado por el equipo.\n'
