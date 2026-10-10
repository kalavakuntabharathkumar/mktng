terraform {
  required_version = ">= 1.5.0"
  required_providers {
    aws = { source = "hashicorp/aws", version = "~> 5.0" }
  }
}
provider "aws" { region = var.aws_region }
variable "aws_region" { type = string; default = "ap-south-1" }
variable "project_name" { type = string; default = "market-ingestion-demo" }
resource "aws_s3_bucket" "raw_feed_archive" {
  bucket = "${var.project_name}-raw-${data.aws_caller_identity.current.account_id}"
  tags = { Project = var.project_name, ManagedBy = "Terraform" }
}
data "aws_caller_identity" "current" {}
output "raw_feed_bucket_name" { value = aws_s3_bucket.raw_feed_archive.bucket }
