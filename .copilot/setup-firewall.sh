#!/bin/bash
# Firewall configuration script for Android HK Vision Muzei Plugin development
# This script configures iptables to allow necessary domains for GitHub Copilot coding agent

# Exit on any error
set -e

echo "Configuring firewall rules for Android development environment..."

# Function to allow HTTPS access to a domain
allow_https_domain() {
    local domain=$1
    echo "Allowing HTTPS access to $domain"
    # Allow outbound HTTPS connections to the domain
    iptables -A OUTPUT -p tcp --dport 443 -d $(dig +short $domain | head -1) -j ACCEPT
}

# Function to allow HTTP access to a domain (for Maven repositories that may need it)
allow_http_domain() {
    local domain=$1
    echo "Allowing HTTP access to $domain"
    # Allow outbound HTTP connections to the domain
    iptables -A OUTPUT -p tcp --dport 80 -d $(dig +short $domain | head -1) -j ACCEPT
}

# Core Android development domains
allow_https_domain "dl.google.com"
allow_https_domain "maven.google.com"
allow_https_domain "repo1.maven.org"
allow_https_domain "repo.maven.apache.org"

# Gradle domains
allow_https_domain "services.gradle.org"
allow_https_domain "downloads.gradle.org"
allow_https_domain "repo.gradle.org"

# Kotlin/JetBrains domains
allow_https_domain "maven.pkg.jetbrains.space"
allow_https_domain "cache-redirector.jetbrains.com"

# Firebase domains
allow_https_domain "firebase.google.com"
allow_https_domain "console.firebase.google.com"

# Application API
allow_https_domain "vision.hossainkhan.com"

# Allow DNS queries (needed for domain resolution)
iptables -A OUTPUT -p udp --dport 53 -j ACCEPT
iptables -A OUTPUT -p tcp --dport 53 -j ACCEPT

echo "Firewall rules configured successfully!"
echo "Note: This script requires root privileges and the 'dig' command to be available."
echo "Please review and test the rules before deploying to production environment."

# Optional: Save the rules (uncomment for persistent rules)
# iptables-save > /etc/iptables/rules.v4