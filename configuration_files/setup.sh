#!/bin/bash

echo "Updating system..."
sudo apt update -y

echo "Installing required packages..."
sudo apt install -y ufw unrar

echo "Enabling firewall..."
sudo ufw --force enable

echo "Allowing required ports..."
for port in 5432 7000 7010
do
    sudo ufw allow ${port}/tcp
done

echo "Firewall status:"
sudo ufw status

echo "Copying environment file..."
SOURCE="/home/aniketvaishampayan1998/HRMS/environment_variable.env"
DEST="/root/environment_variable.env"

if [ -f "$SOURCE" ]; then
    sudo cp "$SOURCE" "$DEST"
    sudo chmod 600 "$DEST"
    echo "File copied and permissions set."
    sudo ls -l "$DEST"
else
    echo "Source file not found: $SOURCE"
fi

echo "Setup completed successfully!"