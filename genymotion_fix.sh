#!/bin/bash

# Fix System genymotion error (Segmentation fault)
# Disables DRI3 to prevent graphics driver crashes
export LIBGL_DRI3_DISABLE=1

echo "Starting Genymotion with fix applied..."
/usr/local/bin/genymotion "$@"
