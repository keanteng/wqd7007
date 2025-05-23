#!/bin/bash

# Script to create a new markdown note with basic template

# Ask the user for a filename
read -p "Enter the filename (without extension): " filename

# Add .md extension if not provided
if [[ ! "$filename" == *.md ]]; then
    filename="${filename}.md"
fi

# Create the file with template content
cat > "$filename" << EOL
# Title

## Configuration

EOL

echo "Created markdown file: $filename"
echo "File created. Use your preferred editor to open it."
