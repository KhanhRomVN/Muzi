#!/bin/bash

# Fix System genymotion error
export LIBGL_DRI3_DISABLE=1

GMTOOL="/home/khanhromvn/genymotion/genymotion/gmtool"
PLAYER="/home/khanhromvn/genymotion/genymotion/player"

# Function to list and select emulator
select_emulator() {
    echo "Checking available emulators..."
    
    # Get list of emulators, excluding the header
    EMA_LIST=$($GMTOOL admin list | grep "|" | grep -v "UUID")
    
    if [ -z "$EMA_LIST" ]; then
        echo "Error: No emulators found. Please create one in Genymotion first."
        exit 1
    fi

    # Parse names and UUIDs into arrays
    IFS=$'\n'
    NAMES=($(echo "$EMA_LIST" | awk -F'|' '{print $4}' | sed 's/^ *//;s/ *$//'))
    UUIDS=($(echo "$EMA_LIST" | awk -F'|' '{print $3}' | sed 's/^ *//;s/ *$//'))
    STATES=($(echo "$EMA_LIST" | awk -F'|' '{print $1}' | sed 's/^ *//;s/ *$//'))
    
    COUNT=${#NAMES[@]}

    if [ "$COUNT" -eq 1 ]; then
        SELECTED_UUID="${UUIDS[0]}"
        SELECTED_NAME="${NAMES[0]}"
        echo "Found one emulator: $SELECTED_NAME"
    else
        echo "Available emulators:"
        for i in "${!NAMES[@]}"; do
            echo "$((i+1)). ${NAMES[$i]} (${STATES[$i]})"
        done
        
        read -p "Select an emulator (1-$COUNT): " CHOICE
        
        if [[ "$CHOICE" =~ ^[0-9]+$ ]] && [ "$CHOICE" -ge 1 ] && [ "$CHOICE" -le "$COUNT" ]; then
            SELECTED_UUID="${UUIDS[$((CHOICE-1))]}"
            SELECTED_NAME="${NAMES[$((CHOICE-1))]}"
        else
            echo "Invalid selection."
            exit 1
        fi
    fi
}

select_emulator

echo "Starting Emulator: $SELECTED_NAME..."
"$PLAYER" --vm-name "$SELECTED_UUID"
